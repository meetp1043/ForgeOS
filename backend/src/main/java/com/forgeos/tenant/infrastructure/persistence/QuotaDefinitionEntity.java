package com.forgeos.tenant.infrastructure.persistence;

import com.forgeos.shared.infrastructure.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "quota_definitions")
public class QuotaDefinitionEntity extends BaseEntity {

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "resource_type", nullable = false)
    private String resourceType; // e.g., WORKFLOW_EXECUTIONS, TOKENS

    @Column(name = "soft_limit", nullable = false)
    private long softLimit;

    @Column(name = "hard_limit", nullable = false)
    private long hardLimit;

    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }

    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public long getSoftLimit() { return softLimit; }
    public void setSoftLimit(long softLimit) { this.softLimit = softLimit; }

    public long getHardLimit() { return hardLimit; }
    public void setHardLimit(long hardLimit) { this.hardLimit = hardLimit; }
}
