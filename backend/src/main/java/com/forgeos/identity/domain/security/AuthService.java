package com.forgeos.identity.domain.security;

import com.forgeos.identity.infrastructure.persistence.RefreshTokenEntity;
import com.forgeos.identity.infrastructure.persistence.RefreshTokenRepository;
import com.forgeos.identity.infrastructure.persistence.UserEntity;
import com.forgeos.identity.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @Value("${forgeos.security.jwt.refresh-token-expiration}")
    private long refreshTokenDurationMs;

    public AuthService(UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

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
        RefreshTokenEntity refreshToken = createRefreshToken(user.getId());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        
        user.setLastLoginAt(Instant.now());
        user.setFailedLoginAttempts(0);
        userRepository.save(user);

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(securityUser);
        RefreshTokenEntity refreshToken = createRefreshToken(user.getId());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (refreshToken.isRevoked() || refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token is expired or revoked");
        }

        UserEntity user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(securityUser);

        // Optionally rotate refresh token here
        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    private RefreshTokenEntity createRefreshToken(UUID userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setRevoked(false);
        return refreshTokenRepository.save(refreshToken);
    }
}
