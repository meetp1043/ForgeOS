package com.forgeos.identity.domain.security;

import com.forgeos.identity.infrastructure.persistence.RefreshTokenEntity;
import com.forgeos.identity.infrastructure.persistence.RefreshTokenRepository;
import com.forgeos.identity.infrastructure.persistence.UserEntity;
import com.forgeos.identity.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final ApplicationEventPublisher eventPublisher;

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final int LOCK_TIME_DURATION_MINUTES = 15;

    @Value("${forgeos.security.jwt.refresh-token-expiration:86400000}")
    private long refreshTokenDurationMs;

    public AuthService(UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       CustomUserDetailsService userDetailsService,
                       ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setStatus("ACTIVE");
        user.setEmailVerified(false);
        userRepository.save(user);

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(securityUser);
        RefreshTokenEntity refreshToken = createRefreshToken(user.getId(), UUID.randomUUID());

        eventPublisher.publishEvent("auth.register.success:" + user.getId());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    @Transactional
    public AuthenticationResponse authenticate(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if ("LOCKED".equals(user.getStatus()) || (user.getLockedUntil() != null && user.getLockedUntil().isAfter(Instant.now()))) {
            eventPublisher.publishEvent("auth.login.failure.locked:" + user.getId());
            throw new IllegalStateException("Account is locked");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            handleFailedLogin(user);
            throw new BadCredentialsException("Invalid email or password");
        }

        user.setLastLoginAt(Instant.now());
        user.setFailedLoginAttempts(0);
        user.setLockedUntil(null);
        user.setStatus("ACTIVE");
        userRepository.save(user);

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(securityUser);
        RefreshTokenEntity refreshToken = createRefreshToken(user.getId(), UUID.randomUUID());

        eventPublisher.publishEvent("auth.login.success:" + user.getId());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    private void handleFailedLogin(UserEntity user) {
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= MAX_FAILED_ATTEMPTS) {
            user.setLockedUntil(Instant.now().plus(LOCK_TIME_DURATION_MINUTES, ChronoUnit.MINUTES));
            user.setStatus("LOCKED");
            eventPublisher.publishEvent("auth.account.locked:" + user.getId());
        }
        userRepository.save(user);
        eventPublisher.publishEvent("auth.login.failure:" + user.getId());
    }

    @Transactional
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        RefreshTokenEntity oldToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (oldToken.isRevoked() || oldToken.getExpiresAt().isBefore(Instant.now())) {
            // Token Reuse Detection
            if (oldToken.isRevoked()) {
                eventPublisher.publishEvent("auth.token.reuse_detected:" + oldToken.getUserId());
                revokeTokenFamily(oldToken.getFamilyId());
            }
            throw new IllegalArgumentException("Refresh token is expired or revoked");
        }

        UserEntity user = userRepository.findById(oldToken.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(securityUser);

        // Rotate token
        oldToken.setRevoked(true);
        RefreshTokenEntity newToken = createRefreshToken(user.getId(), oldToken.getFamilyId());
        oldToken.setReplacedByToken(newToken.getToken());
        refreshTokenRepository.save(oldToken);

        eventPublisher.publishEvent("auth.token.refresh:" + user.getId());

        return new AuthenticationResponse(jwtToken, newToken.getToken());
    }

    private void revokeTokenFamily(UUID familyId) {
        // Not optimized, but illustrates family revocation
        List<RefreshTokenEntity> allTokens = refreshTokenRepository.findAll();
        for (RefreshTokenEntity t : allTokens) {
            if (familyId.equals(t.getFamilyId())) {
                t.setRevoked(true);
                refreshTokenRepository.save(t);
            }
        }
    }

    private RefreshTokenEntity createRefreshToken(UUID userId, UUID familyId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setRevoked(false);
        refreshToken.setFamilyId(familyId);
        return refreshTokenRepository.save(refreshToken);
    }
}
