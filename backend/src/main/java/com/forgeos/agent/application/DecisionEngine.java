package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentDecision;
import com.forgeos.agent.domain.AgentExecution;

public interface DecisionEngine {
    
    /**
     * Interprets raw output from the Model Gateway into a structured AgentDecision.
     */
    AgentDecision parseDecision(AgentExecution execution, String modelOutput);
}
