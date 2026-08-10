package com.forgeos.identity.domain.security;

import com.forgeos.identity.infrastructure.persistence.UserEntity;
import com.forgeos.identity.infrastructure.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean accountNonLocked = userEntity.getLockedUntil() == null || userEntity.getLockedUntil().isBefore(Instant.now());
        boolean enabled = "ACTIVE".equals(userEntity.getStatus());

        return new SecurityUser(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPasswordHash(),
                Collections.emptyList(), // Authorities are managed separately via Tenant/Organization
                enabled,
                accountNonLocked
        );
    }
}
