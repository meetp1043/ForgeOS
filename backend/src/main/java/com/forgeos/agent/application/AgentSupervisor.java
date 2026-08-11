package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.AgentResult;
import com.forgeos.agent.domain.AgentRole;

import java.util.UUID;

public interface AgentSupervisor {

    /**
     * Creates a governed execution context for an agent.
     */
    AgentExecution createExecution(AgentRole role, UUID tenantId, UUID projectId, String objective, String workspacePath);

    /**
     * Resumes or starts the executor loop.
     */
    AgentResult startExecution(UUID executionId);
    
    /**
     * Validates if the execution is still within policy limits.
     */
    void enforceLimits(AgentExecution execution);
}
