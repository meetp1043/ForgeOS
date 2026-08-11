package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.ExecutionStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgentExecutionLoop {

    private final AgentLifecycleManager lifecycleManager;

    public AgentExecutionLoop(AgentLifecycleManager lifecycleManager) {
        this.lifecycleManager = lifecycleManager;
    }

    public void executeLoop(UUID executionId) {
        AgentExecution execution = lifecycleManager.getExecution(executionId);
        
        while (isLoopActive(execution.getStatus())) {
            // Budget check
            if (execution.getBudget().isExhausted()) {
                lifecycleManager.failExecution(executionId, "Budget exhausted");
                break;
            }

            switch (execution.getStatus()) {
                case CREATED:
                    lifecycleManager.transitionState(executionId, ExecutionStatus.INITIALIZING);
                    break;
                case INITIALIZING:
                    lifecycleManager.transitionState(executionId, ExecutionStatus.PLANNING);
                    break;
                case PLANNING:
                    // Simulated plan creation
                    lifecycleManager.transitionState(executionId, ExecutionStatus.EXECUTING);
                    break;
                case EXECUTING:
                    // Simulated tool call
                    execution.getBudget().consumeToolCall();
                    lifecycleManager.transitionState(executionId, ExecutionStatus.WAITING_FOR_TOOL);
                    break;
                case WAITING_FOR_TOOL:
                    // Simulated observation
                    lifecycleManager.transitionState(executionId, ExecutionStatus.OBSERVING);
                    break;
                case OBSERVING:
                    // Simulated validation check (normally evaluating plan completeness)
                    lifecycleManager.transitionState(executionId, ExecutionStatus.VALIDATING);
                    break;
                case VALIDATING:
                    lifecycleManager.transitionState(executionId, ExecutionStatus.COMPLETING);
                    break;
                case COMPLETING:
                    lifecycleManager.transitionState(executionId, ExecutionStatus.COMPLETED);
                    break;
                default:
                    throw new IllegalStateException("Unexpected state in active loop: " + execution.getStatus());
            }
        }
    }

    private boolean isLoopActive(ExecutionStatus status) {
        return status != ExecutionStatus.COMPLETED &&
               status != ExecutionStatus.FAILED &&
               status != ExecutionStatus.CANCELLED &&
               status != ExecutionStatus.TIMED_OUT &&
               status != ExecutionStatus.QUARANTINED &&
               status != ExecutionStatus.WAITING_FOR_APPROVAL; // Approvals break the active synchronous loop
    }
}
