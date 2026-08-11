package com.forgeos.agent.domain;

import java.util.UUID;

public class AgentExecution {
    private UUID id;
    private UUID agentId;
    private String agentVersion;
    private UUID parentExecutionId;
    private UUID tenantId;
    private UUID projectId;
    private String workspacePath;
    private AgentExecutionStatus status;
    private String objective;
    private int maxSteps;
    private int currentStep;
    private Long budgetTokens;
    private Long consumedTokens;
    private String correlationId;

    public AgentExecution() {
        this.id = UUID.randomUUID();
        this.status = AgentExecutionStatus.CREATED;
        this.currentStep = 0;
        this.consumedTokens = 0L;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAgentId() { return agentId; }
    public void setAgentId(UUID agentId) { this.agentId = agentId; }
    public String getAgentVersion() { return agentVersion; }
    public void setAgentVersion(String agentVersion) { this.agentVersion = agentVersion; }
    public UUID getParentExecutionId() { return parentExecutionId; }
    public void setParentExecutionId(UUID parentExecutionId) { this.parentExecutionId = parentExecutionId; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public String getWorkspacePath() { return workspacePath; }
    public void setWorkspacePath(String workspacePath) { this.workspacePath = workspacePath; }
    public AgentExecutionStatus getStatus() { return status; }
    public void setStatus(AgentExecutionStatus status) { this.status = status; }
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    public int getMaxSteps() { return maxSteps; }
    public void setMaxSteps(int maxSteps) { this.maxSteps = maxSteps; }
    public int getCurrentStep() { return currentStep; }
    public void setCurrentStep(int currentStep) { this.currentStep = currentStep; }
    public Long getBudgetTokens() { return budgetTokens; }
    public void setBudgetTokens(Long budgetTokens) { this.budgetTokens = budgetTokens; }
    public Long getConsumedTokens() { return consumedTokens; }
    public void setConsumedTokens(Long consumedTokens) { this.consumedTokens = consumedTokens; }
    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
}
