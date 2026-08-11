package com.forgeos.context.domain;

import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.time.OffsetDateTime;

public class ContextPack {
    private UUID contextId;
    private UUID tenantId;
    private UUID projectId;
    private OffsetDateTime createdAt;
    private Map<ContextSection, List<ContextItem>> sections;
    private int estimatedTokens;

    public UUID getContextId() { return contextId; }
    public void setContextId(UUID contextId) { this.contextId = contextId; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public Map<ContextSection, List<ContextItem>> getSections() { return sections; }
    public void setSections(Map<ContextSection, List<ContextItem>> sections) { this.sections = sections; }
    public int getEstimatedTokens() { return estimatedTokens; }
    public void setEstimatedTokens(int estimatedTokens) { this.estimatedTokens = estimatedTokens; }
}
