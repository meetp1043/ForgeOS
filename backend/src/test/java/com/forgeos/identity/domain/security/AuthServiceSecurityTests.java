package com.forgeos.identity.domain.security;

import com.forgeos.identity.infrastructure.persistence.RefreshTokenEntity;
import com.forgeos.identity.infrastructure.persistence.RefreshTokenRepository;
import com.forgeos.identity.infrastructure.persistence.UserEntity;
import com.forgeos.identity.infrastructure.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceSecurityTests {

    private AuthService authService;
    private UserRepository userRepository;
    private RefreshTokenRepository refreshTokenRepository;
    private AuthenticationManager authenticationManager;
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        refreshTokenRepository = mock(RefreshTokenRepository.class);
        authenticationManager = mock(AuthenticationManager.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        
        authService = new AuthService(
                userRepository,
                refreshTokenRepository,
                mock(PasswordEncoder.class),
                mock(JwtService.class),
                authenticationManager,
                mock(CustomUserDetailsService.class),
                eventPublisher
        );
    }

    @Test
    void testAccountLockoutAfterMaxFailures() {
        UserEntity user = new UserEntity();
        user.setEmail("test@test.com");
        user.setStatus("ACTIVE");
        user.setFailedLoginAttempts(4);

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad auth"));

        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("wrong");

        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));

        assertEquals("LOCKED", user.getStatus());
        assertNotNull(user.getLockedUntil());
        verify(eventPublisher).publishEvent(startsWith("auth.account.locked"));
    }

    @Test
    void testLockedAccountCannotLogin() {
        UserEntity user = new UserEntity();
        user.setEmail("test@test.com");
        user.setStatus("LOCKED");
        user.setLockedUntil(Instant.now().plusSeconds(600));

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("correct");

        assertThrows(IllegalStateException.class, () -> authService.authenticate(request));
    }
    
    @Test
    void testTokenReuseRevokesFamily() {
        RefreshTokenEntity revokedToken = new RefreshTokenEntity();
        revokedToken.setUserId(UUID.randomUUID());
        revokedToken.setRevoked(true); // Already revoked (it was rotated)
        revokedToken.setExpiresAt(Instant.now().plusSeconds(100));
        UUID familyId = UUID.randomUUID();
        revokedToken.setFamilyId(familyId);
        
        when(refreshTokenRepository.findByToken("stolen-token")).thenReturn(Optional.of(revokedToken));
        
        RefreshTokenEntity activeToken = new RefreshTokenEntity();
        activeToken.setFamilyId(familyId);
        activeToken.setRevoked(false);
        when(refreshTokenRepository.findAll()).thenReturn(List.of(activeToken, revokedToken));
        
        RefreshTokenRequest request = new RefreshTokenRequest();
        request.setRefreshToken("stolen-token");
        
        assertThrows(IllegalArgumentException.class, () -> authService.refreshToken(request));
        
        // Assert that the other token in the family got revoked due to reuse detection
        assertTrue(activeToken.isRevoked());
        verify(refreshTokenRepository).save(activeToken);
        verify(eventPublisher).publishEvent(startsWith("auth.token.reuse_detected"));
    }
}
