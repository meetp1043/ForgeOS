package com.forgeos.context.domain;

import java.util.UUID;
import java.util.List;

public class ContextRequest {
    private UUID tenantId;
    private UUID organizationId;
    private UUID projectId;
    private UUID workspaceId;
    private UUID workflowId;
    private UUID taskId;
    private String agentRole;
    private String objective;
    private String query;
    private int contextBudgetTokens;

    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getOrganizationId() { return organizationId; }
    public void setOrganizationId(UUID organizationId) { this.organizationId = organizationId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public UUID getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(UUID workspaceId) { this.workspaceId = workspaceId; }
    public UUID getWorkflowId() { return workflowId; }
    public void setWorkflowId(UUID workflowId) { this.workflowId = workflowId; }
    public UUID getTaskId() { return taskId; }
    public void setTaskId(UUID taskId) { this.taskId = taskId; }
    public String getAgentRole() { return agentRole; }
    public void setAgentRole(String agentRole) { this.agentRole = agentRole; }
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    public int getContextBudgetTokens() { return contextBudgetTokens; }
    public void setContextBudgetTokens(int contextBudgetTokens) { this.contextBudgetTokens = contextBudgetTokens; }
}
