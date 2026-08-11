package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.AgentExecutionStatus;
import com.forgeos.agent.domain.AgentResult;
import com.forgeos.agent.domain.AgentRole;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgentSupervisorImpl implements AgentSupervisor {

    private final AgentExecutor executor;

    public AgentSupervisorImpl(AgentExecutor executor) {
        this.executor = executor;
    }

    @Override
    public AgentExecution createExecution(AgentRole role, UUID tenantId, UUID projectId, String objective, String workspacePath) {
        AgentExecution execution = new AgentExecution();
        execution.setTenantId(tenantId);
        execution.setProjectId(projectId);
        execution.setObjective(objective);
        execution.setWorkspacePath(workspacePath);
        execution.setMaxSteps(50); // Default limit
        return execution;
    }

    @Override
    public AgentResult startExecution(UUID executionId) {
        // In a real implementation, we would load the execution from the database.
        // For architectural setup, we mock the lookup and pass to the Executor.
        AgentExecution dummyExecution = new AgentExecution();
        dummyExecution.setId(executionId);
        dummyExecution.setStatus(AgentExecutionStatus.RUNNING);
        
        return executor.execute(dummyExecution);
    }

    @Override
    public void enforceLimits(AgentExecution execution) {
        if (execution.getCurrentStep() >= execution.getMaxSteps()) {
            execution.setStatus(AgentExecutionStatus.FAILED);
            throw new RuntimeException("Agent execution exceeded maximum steps limit of " + execution.getMaxSteps());
        }
        
        if (execution.getBudgetTokens() != null && execution.getConsumedTokens() > execution.getBudgetTokens()) {
            execution.setStatus(AgentExecutionStatus.ESCALATED);
            throw new RuntimeException("Agent execution exceeded budget limit.");
        }
    }
}
