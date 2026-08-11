package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;

/**
 * Interface boundary to the Context Engine (Phase 07 specification).
 * Will retrieve relevant project context dynamically.
 */
public interface ContextProvider {
    String getRelevantContext(AgentExecution execution, String objective);
}
