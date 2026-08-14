package com.forgeos.identity.domain.security.policy;

import com.forgeos.identity.domain.security.Role;
import com.forgeos.identity.domain.security.SecurityUser;

import java.util.UUID;

public class ForgeSecurityContext {
    private final SecurityUser user;
    private final UUID tenantId;
    private final UUID projectId;
    private final Role role;

    public ForgeSecurityContext(SecurityUser user, UUID tenantId, UUID projectId, Role role) {
        this.user = user;
        this.tenantId = tenantId;
        this.projectId = projectId;
        this.role = role;
    }

    public SecurityUser getUser() { return user; }
    public UUID getTenantId() { return tenantId; }
    public UUID getProjectId() { return projectId; }
    public Role getRole() { return role; }
}
