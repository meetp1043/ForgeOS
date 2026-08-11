package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.ExecutionStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgentLifecycleManager {

    private final StateTransitionEngine stateTransitionEngine;
    private final Map<UUID, AgentExecution> activeExecutions = new ConcurrentHashMap<>();

    public AgentLifecycleManager(StateTransitionEngine stateTransitionEngine) {
        this.stateTransitionEngine = stateTransitionEngine;
    }

    public void registerExecution(AgentExecution execution) {
        activeExecutions.put(execution.getExecutionId(), execution);
    }

    public void transitionState(UUID executionId, ExecutionStatus newStatus) {
        AgentExecution execution = getExecutionOrThrow(executionId);
        stateTransitionEngine.validateTransition(execution.getStatus(), newStatus);
        execution.setStatus(newStatus);
    }

    public void cancelExecution(UUID executionId) {
        transitionState(executionId, ExecutionStatus.CANCELLED);
    }

    public void failExecution(UUID executionId, String reason) {
        AgentExecution execution = getExecutionOrThrow(executionId);
        stateTransitionEngine.validateTransition(execution.getStatus(), ExecutionStatus.FAILED);
        execution.setStatus(ExecutionStatus.FAILED);
        execution.setFailureReason(reason);
    }

    public AgentExecution getExecution(UUID executionId) {
        return activeExecutions.get(executionId);
    }

    private AgentExecution getExecutionOrThrow(UUID executionId) {
        AgentExecution execution = activeExecutions.get(executionId);
        if (execution == null) {
            throw new IllegalArgumentException("Execution not found: " + executionId);
        }
        return execution;
    }
}
