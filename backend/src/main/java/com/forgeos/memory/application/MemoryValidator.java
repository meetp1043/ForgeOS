package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryCandidate;

public interface MemoryValidator {
    /**
     * Validates a candidate before durable persistence. Checks for secrets, PII, and impossible scopes.
     * Throws an exception if validation fails.
     */
    void validate(MemoryCandidate candidate);
}
