package com.forgeos.tools.domain;

import java.util.Map;
import java.util.UUID;

public class ToolRequest {
    private String requestId;
    private String toolId;
    private String toolVersion;
    private UUID actorId;
    private ActorType actorType;
    private UUID tenantId;
    private UUID organizationId;
    private UUID projectId;
    private String workspaceRoot;
    private UUID agentExecutionId;
    private Map<String, Object> arguments;
    private ToolRiskLevel risk;
    private ExecutionEnvironment environment;
    private Long timeoutMs;
    private String correlationId;

    public ToolRequest() {
        this.requestId = UUID.randomUUID().toString();
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    
    public String getToolId() { return toolId; }
    public void setToolId(String toolId) { this.toolId = toolId; }
    
    public String getToolVersion() { return toolVersion; }
    public void setToolVersion(String toolVersion) { this.toolVersion = toolVersion; }

    public UUID getActorId() { return actorId; }
    public void setActorId(UUID actorId) { this.actorId = actorId; }
    
    public ActorType getActorType() { return actorType; }
    public void setActorType(ActorType actorType) { this.actorType = actorType; }
    
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    
    public UUID getOrganizationId() { return organizationId; }
    public void setOrganizationId(UUID organizationId) { this.organizationId = organizationId; }
    
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    
    public String getWorkspaceRoot() { return workspaceRoot; }
    public void setWorkspaceRoot(String workspaceRoot) { this.workspaceRoot = workspaceRoot; }

    public UUID getAgentExecutionId() { return agentExecutionId; }
    public void setAgentExecutionId(UUID agentExecutionId) { this.agentExecutionId = agentExecutionId; }
    
    public Map<String, Object> getArguments() { return arguments; }
    public void setArguments(Map<String, Object> arguments) { this.arguments = arguments; }
    
    public ToolRiskLevel getRisk() { return risk; }
    public void setRisk(ToolRiskLevel risk) { this.risk = risk; }
    
    public ExecutionEnvironment getEnvironment() { return environment; }
    public void setEnvironment(ExecutionEnvironment environment) { this.environment = environment; }

    public Long getTimeoutMs() { return timeoutMs; }
    public void setTimeoutMs(Long timeoutMs) { this.timeoutMs = timeoutMs; }
    
    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
}
