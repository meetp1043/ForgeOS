package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;

public interface AgentPlanner {
    /**
     * Generates a step-by-step plan for the given objective.
     */
    String generatePlan(AgentExecution execution, String objective);
}
