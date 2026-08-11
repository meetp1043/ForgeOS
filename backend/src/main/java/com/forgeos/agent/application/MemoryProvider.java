package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;

/**
 * Interface boundary to the Memory Engine (Phase 06 specification).
 */
public interface MemoryProvider {
    String retrieveMemory(AgentExecution execution);
    void storeMemory(AgentExecution execution, String key, String value);
}
