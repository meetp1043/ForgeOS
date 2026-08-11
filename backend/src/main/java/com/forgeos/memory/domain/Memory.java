package com.forgeos.memory.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.Map;

public class Memory {
    private UUID id;
    private UUID tenantId;
    private UUID organizationId;
    private UUID projectId;
    private UUID workspaceId;
    
    private MemoryScope scope;
    private MemoryType type;
    
    private String title;
    private String summary;
    private String content;
    
    private String source;
    private String sourceReference;
    private UUID createdBy;
    
    private MemoryImportance importance;
    private MemoryConfidence confidence;
    private MemoryStatus status;
    private MemoryAuthority authority;
    private MemorySecurityClassification securityClassification;
    
    private int version;
    private Map<String, Object> metadata;
    
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime expiresAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getOrganizationId() { return organizationId; }
    public void setOrganizationId(UUID organizationId) { this.organizationId = organizationId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public UUID getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(UUID workspaceId) { this.workspaceId = workspaceId; }
    public MemoryScope getScope() { return scope; }
    public void setScope(MemoryScope scope) { this.scope = scope; }
    public MemoryType getType() { return type; }
    public void setType(MemoryType type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getSourceReference() { return sourceReference; }
    public void setSourceReference(String sourceReference) { this.sourceReference = sourceReference; }
    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
    public MemoryImportance getImportance() { return importance; }
    public void setImportance(MemoryImportance importance) { this.importance = importance; }
    public MemoryConfidence getConfidence() { return confidence; }
    public void setConfidence(MemoryConfidence confidence) { this.confidence = confidence; }
    public MemoryStatus getStatus() { return status; }
    public void setStatus(MemoryStatus status) { this.status = status; }
    public MemoryAuthority getAuthority() { return authority; }
    public void setAuthority(MemoryAuthority authority) { this.authority = authority; }
    public MemorySecurityClassification getSecurityClassification() { return securityClassification; }
    public void setSecurityClassification(MemorySecurityClassification securityClassification) { this.securityClassification = securityClassification; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public OffsetDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(OffsetDateTime expiresAt) { this.expiresAt = expiresAt; }
}
