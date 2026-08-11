package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.AgentResult;

public interface AgentExecutor {

    /**
     * Executes the ReAct loop (Reason, Act, Observe) until completion or failure.
     */
    AgentResult execute(AgentExecution execution);
}
