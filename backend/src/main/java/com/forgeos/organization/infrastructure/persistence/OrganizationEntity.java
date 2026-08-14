package com.forgeos.organization.infrastructure.persistence;

import com.forgeos.shared.infrastructure.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizations")
public class OrganizationEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "tenant_id", nullable = false)
    private java.util.UUID tenantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(java.util.UUID tenantId) {
        this.tenantId = tenantId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
