package com.forgeos.memory.domain;

import java.util.UUID;
import java.time.OffsetDateTime;

public class MemoryCandidate {
    private UUID id;
    private UUID tenantId;
    private UUID projectId;
    private MemoryScope scope;
    private MemoryType type;
    private String title;
    private String content;
    private String source;
    private String sourceReference;
    private MemoryConfidence confidence;
    private MemoryImportance importance;
    private MemorySecurityClassification securityClassification;
    private OffsetDateTime createdAt;

    public MemoryCandidate() {
        this.id = UUID.randomUUID();
        this.createdAt = OffsetDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public MemoryScope getScope() { return scope; }
    public void setScope(MemoryScope scope) { this.scope = scope; }
    public MemoryType getType() { return type; }
    public void setType(MemoryType type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getSourceReference() { return sourceReference; }
    public void setSourceReference(String sourceReference) { this.sourceReference = sourceReference; }
    public MemoryConfidence getConfidence() { return confidence; }
    public void setConfidence(MemoryConfidence confidence) { this.confidence = confidence; }
    public MemoryImportance getImportance() { return importance; }
    public void setImportance(MemoryImportance importance) { this.importance = importance; }
    public MemorySecurityClassification getSecurityClassification() { return securityClassification; }
    public void setSecurityClassification(MemorySecurityClassification securityClassification) { this.securityClassification = securityClassification; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
