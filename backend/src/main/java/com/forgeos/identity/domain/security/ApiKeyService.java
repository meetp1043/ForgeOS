package com.forgeos.identity.domain.security;

import com.forgeos.identity.infrastructure.persistence.ApiKeyEntity;
import com.forgeos.identity.infrastructure.persistence.ApiKeyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiKeyService(ApiKeyRepository apiKeyRepository, PasswordEncoder passwordEncoder) {
        this.apiKeyRepository = apiKeyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String generateApiKey(UUID tenantId, String name, String permissions, Instant expiresAt) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String secret = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        String prefix = secret.substring(0, 8);

        ApiKeyEntity apiKey = new ApiKeyEntity();
        apiKey.setTenantId(tenantId);
        apiKey.setName(name);
        apiKey.setPrefix(prefix);
        apiKey.setHashedSecret(passwordEncoder.encode(secret));
        apiKey.setPermissions(permissions);
        apiKey.setStatus("ACTIVE");
        apiKey.setExpiresAt(expiresAt);

        apiKeyRepository.save(apiKey);

        // This is the ONLY time the raw secret is returned
        return secret;
    }

    public boolean validateApiKey(String rawSecret) {
        if (rawSecret == null || rawSecret.length() < 8) return false;

        String prefix = rawSecret.substring(0, 8);
        Optional<ApiKeyEntity> optionalKey = apiKeyRepository.findByPrefix(prefix);

        if (optionalKey.isEmpty()) return false;

        ApiKeyEntity apiKey = optionalKey.get();

        if (!"ACTIVE".equals(apiKey.getStatus())) return false;
        if (apiKey.getExpiresAt() != null && apiKey.getExpiresAt().isBefore(Instant.now())) return false;

        boolean isValid = passwordEncoder.matches(rawSecret, apiKey.getHashedSecret());
        
        if (isValid) {
            apiKey.setLastUsedAt(Instant.now());
            apiKeyRepository.save(apiKey);
        }
        
        return isValid;
    }

    public List<ApiKeyEntity> getApiKeysByTenant(UUID tenantId) {
        return apiKeyRepository.findByTenantId(tenantId);
    }

    public void revokeApiKey(UUID id) {
        ApiKeyEntity key = apiKeyRepository.findById(id).orElseThrow();
        key.setStatus("REVOKED");
        apiKeyRepository.save(key);
    }
}
