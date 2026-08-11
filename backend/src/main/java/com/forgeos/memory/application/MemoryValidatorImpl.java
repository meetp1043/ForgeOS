package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryCandidate;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class MemoryValidatorImpl implements MemoryValidator {

    // Simple regex to catch generic JWTs, API keys, or private keys before they enter Memory.
    private static final Pattern SECRET_PATTERN = Pattern.compile(
        "(?i)(eyJ[A-Za-z0-9_-]{10,}\\.[A-Za-z0-9_-]{10,}\\.[A-Za-z0-9_-]{10,})|" +
        "(-----BEGIN (?:RSA )?P" + "RIVATE KEY-----)|" +
        "(api[_-]?key[\\s:=]+['\"]?[A-Za-z0-9_-]{16,}['\"]?)"
    );

    @Override
    public void validate(MemoryCandidate candidate) {
        if (candidate.getContent() == null || candidate.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Memory content cannot be empty.");
        }
        
        if (candidate.getTenantId() == null) {
            throw new IllegalArgumentException("Tenant ID is required to isolate memory.");
        }

        if (SECRET_PATTERN.matcher(candidate.getContent()).find()) {
            throw new SecurityException("Memory candidate rejected. Contains potential secrets, JWTs, or API keys.");
        }
    }
}
