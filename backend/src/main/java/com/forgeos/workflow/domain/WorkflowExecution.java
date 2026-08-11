package com.forgeos.workflow.domain;

import java.util.UUID;
import java.time.OffsetDateTime;

public class WorkflowExecution {
    private UUID id;
    private UUID workflowDefinitionId;
    private String workflowVersion;
    private UUID tenantId;
    private UUID projectId;
    private WorkflowStatus status;
    private String objective;
    private Long budgetTokens;
    private OffsetDateTime startedAt;
    private OffsetDateTime completedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWorkflowDefinitionId() { return workflowDefinitionId; }
    public void setWorkflowDefinitionId(UUID workflowDefinitionId) { this.workflowDefinitionId = workflowDefinitionId; }
    public String getWorkflowVersion() { return workflowVersion; }
    public void setWorkflowVersion(String workflowVersion) { this.workflowVersion = workflowVersion; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public WorkflowStatus getStatus() { return status; }
    public void setStatus(WorkflowStatus status) { this.status = status; }
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    public Long getBudgetTokens() { return budgetTokens; }
    public void setBudgetTokens(Long budgetTokens) { this.budgetTokens = budgetTokens; }
    public OffsetDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(OffsetDateTime completedAt) { this.completedAt = completedAt; }
}
