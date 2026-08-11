package com.forgeos.agent.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AgentExecution {
    private UUID executionId;
    private UUID agentId;
    private ExecutionStatus status;
    private AgentBudget budget;
    private UUID currentPlanId;
    private OffsetDateTime startedAt;
    private String failureReason;

    public AgentExecution(UUID executionId, UUID agentId, AgentBudget budget) {
        this.executionId = executionId;
        this.agentId = agentId;
        this.budget = budget;
        this.status = ExecutionStatus.CREATED;
        this.startedAt = OffsetDateTime.now();
    }

    public UUID getExecutionId() { return executionId; }
    public UUID getAgentId() { return agentId; }
    public ExecutionStatus getStatus() { return status; }
    public AgentBudget getBudget() { return budget; }
    public UUID getCurrentPlanId() { return currentPlanId; }
    public String getFailureReason() { return failureReason; }

    public void setStatus(ExecutionStatus status) { this.status = status; }
    public void setCurrentPlanId(UUID currentPlanId) { this.currentPlanId = currentPlanId; }
    public void setFailureReason(String reason) { this.failureReason = reason; }
}
