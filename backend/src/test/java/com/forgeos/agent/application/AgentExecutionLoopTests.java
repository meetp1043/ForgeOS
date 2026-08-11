package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentBudget;
import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.ExecutionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentExecutionLoopTests {

    private AgentLifecycleManager lifecycleManager;
    private AgentExecutionLoop loop;

    @BeforeEach
    void setUp() {
        StateTransitionEngine stateEngine = new StateTransitionEngine();
        lifecycleManager = new AgentLifecycleManager(stateEngine);
        loop = new AgentExecutionLoop(lifecycleManager);
    }

    @Test
    void fullExecutionCompletesSuccessfully() {
        UUID executionId = UUID.randomUUID();
        AgentBudget budget = new AgentBudget(1000, 10);
        AgentExecution execution = new AgentExecution(executionId, UUID.randomUUID(), budget);
        lifecycleManager.registerExecution(execution);

        loop.executeLoop(executionId);

        assertEquals(ExecutionStatus.COMPLETED, execution.getStatus());
        assertEquals(1, execution.getBudget().getUsedToolCalls()); // Our simulated loop does 1 tool call
    }

    @Test
    void loopExitsWhenBudgetExhausted() {
        UUID executionId = UUID.randomUUID();
        // Give 0 tool calls max
        AgentBudget budget = new AgentBudget(1000, 0);
        AgentExecution execution = new AgentExecution(executionId, UUID.randomUUID(), budget);
        // Put in executing state, where a tool call happens
        execution.setStatus(ExecutionStatus.EXECUTING);
        lifecycleManager.registerExecution(execution);

        loop.executeLoop(executionId);

        assertEquals(ExecutionStatus.FAILED, execution.getStatus());
        assertTrue(execution.getFailureReason().contains("Budget exhausted"));
    }
}
