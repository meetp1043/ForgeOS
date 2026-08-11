package com.forgeos.git.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Repository {
    private UUID repositoryId;
    private UUID tenantId;
    private UUID projectId;
    private String provider; // e.g., "GITHUB"
    private String providerRepositoryId;
    private String fullName; // e.g., "meetp1043/ForgeOS"
    private String cloneUrl;
    private String defaultBranch;
    private boolean isPrivate;
    private OffsetDateTime createdAt;

    public UUID getRepositoryId() { return repositoryId; }
    public void setRepositoryId(UUID repositoryId) { this.repositoryId = repositoryId; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getProjectId() { return projectId; }
    public void setProjectId(UUID projectId) { this.projectId = projectId; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getProviderRepositoryId() { return providerRepositoryId; }
    public void setProviderRepositoryId(String providerRepositoryId) { this.providerRepositoryId = providerRepositoryId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getCloneUrl() { return cloneUrl; }
    public void setCloneUrl(String cloneUrl) { this.cloneUrl = cloneUrl; }
    public String getDefaultBranch() { return defaultBranch; }
    public void setDefaultBranch(String defaultBranch) { this.defaultBranch = defaultBranch; }
    public boolean isPrivate() { return isPrivate; }
    public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
