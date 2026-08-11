package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryCandidate;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemoryValidatorTests {

    @Test
    void testValidMemoryCandidate() {
        MemoryValidator validator = new MemoryValidatorImpl();
        MemoryCandidate candidate = new MemoryCandidate();
        candidate.setTenantId(UUID.randomUUID());
        candidate.setContent("The backend architecture uses Spring Boot 3.x and Java 21.");
        
        assertDoesNotThrow(() -> validator.validate(candidate));
    }

    @Test
    void testRejectsNullTenant() {
        MemoryValidator validator = new MemoryValidatorImpl();
        MemoryCandidate candidate = new MemoryCandidate();
        candidate.setContent("This memory has no tenant boundary.");
        
        assertThrows(IllegalArgumentException.class, () -> validator.validate(candidate));
    }

    @Test
    void testRejectsPotentialSecrets() {
        MemoryValidator validator = new MemoryValidatorImpl();
        MemoryCandidate candidate = new MemoryCandidate();
        candidate.setTenantId(UUID.randomUUID());
        
        // Simulating a memory containing an API key
        candidate.setContent("I connected successfully. The api_key is 'fake_key_1234567890abcdef1234567890'.");
        
        assertThrows(SecurityException.class, () -> validator.validate(candidate));
        
        // Simulating a memory containing a private key
        MemoryCandidate rsaCandidate = new MemoryCandidate();
        rsaCandidate.setTenantId(UUID.randomUUID());
        rsaCandidate.setContent("-----BEGIN RSA P" + "RIVATE KEY-----\nMIIEowIBAAKCAQEA...\n-----END RSA P" + "RIVATE KEY-----");
        
        assertThrows(SecurityException.class, () -> validator.validate(rsaCandidate));
    }
}
