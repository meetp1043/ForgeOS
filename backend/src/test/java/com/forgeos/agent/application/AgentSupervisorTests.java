package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.AgentExecutionStatus;
import com.forgeos.agent.domain.AgentRole;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AgentSupervisorTests {

    @Test
    void testLimitEnforcementStopsExecution() {
        AgentExecutor dummyExecutor = execution -> null;
        AgentSupervisor supervisor = new AgentSupervisorImpl(dummyExecutor);

        AgentExecution execution = supervisor.createExecution(
                AgentRole.BACKEND_ENGINEER,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Fix the bug",
                "/tmp/workspace"
        );
        
        execution.setMaxSteps(5);
        execution.setCurrentStep(5);

        assertThrows(RuntimeException.class, () -> supervisor.enforceLimits(execution));
        assertEquals(AgentExecutionStatus.FAILED, execution.getStatus());
    }

    @Test
    void testBudgetEnforcementStopsExecution() {
        AgentExecutor dummyExecutor = execution -> null;
        AgentSupervisor supervisor = new AgentSupervisorImpl(dummyExecutor);

        AgentExecution execution = supervisor.createExecution(
                AgentRole.FRONTEND_ENGINEER,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Build UI",
                "/tmp/workspace"
        );
        
        execution.setBudgetTokens(100L);
        execution.setConsumedTokens(150L);

        assertThrows(RuntimeException.class, () -> supervisor.enforceLimits(execution));
        assertEquals(AgentExecutionStatus.ESCALATED, execution.getStatus());
    }
}
