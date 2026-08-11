package com.forgeos.memory.domain;

import java.util.List;
import java.util.UUID;

public class MemoryQuery {
    private String text;
    private UUID tenantId;
    private UUID projectId;
    private List<MemoryScope> scopes;
    private List<MemoryType> types;
    private MemoryImportance minimumImportance;
    private MemoryConfidence minimumConfidence;
    private MemorySecurityClassification maximumSecurityClassification;
    private int limit = 10;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public List<MemoryScope> getScopes() { return scopes; }
    public void setScopes(List<MemoryScope> scopes) { this.scopes = scopes; }
    public List<MemoryType> getTypes() { return types; }
    public void setTypes(List<MemoryType> types) { this.types = types; }
    public MemoryImportance getMinimumImportance() { return minimumImportance; }
    public void setMinimumImportance(MemoryImportance minimumImportance) { this.minimumImportance = minimumImportance; }
    public MemoryConfidence getMinimumConfidence() { return minimumConfidence; }
    public void setMinimumConfidence(MemoryConfidence minimumConfidence) { this.minimumConfidence = minimumConfidence; }
    public MemorySecurityClassification getMaximumSecurityClassification() { return maximumSecurityClassification; }
    public void setMaximumSecurityClassification(MemorySecurityClassification maximumSecurityClassification) { this.maximumSecurityClassification = maximumSecurityClassification; }
    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }
}
