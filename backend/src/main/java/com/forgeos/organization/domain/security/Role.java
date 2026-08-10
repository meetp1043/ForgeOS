package com.forgeos.organization.domain.security;

import java.util.Set;

public enum Role {
    OWNER(Set.of(Permission.values())),
    
    ADMIN(Set.of(
            Permission.ORGANIZATION_READ,
            Permission.ORGANIZATION_UPDATE,
            Permission.MEMBER_READ,
            Permission.MEMBER_INVITE,
            Permission.MEMBER_REMOVE,
            Permission.PROJECT_CREATE,
            Permission.PROJECT_READ,
            Permission.PROJECT_UPDATE,
            Permission.PROJECT_DELETE,
            Permission.WORKSPACE_READ,
            Permission.WORKSPACE_WRITE,
            Permission.AGENT_READ,
            Permission.AGENT_CREATE,
            Permission.AGENT_UPDATE,
            Permission.AGENT_EXECUTE,
            Permission.WORKFLOW_READ,
            Permission.WORKFLOW_CREATE,
            Permission.WORKFLOW_EXECUTE,
            Permission.ARTIFACT_READ,
            Permission.ARTIFACT_WRITE,
            Permission.APPROVAL_READ,
            Permission.APPROVAL_DECIDE,
            Permission.AUDIT_READ
    )),
    
    MEMBER(Set.of(
            Permission.ORGANIZATION_READ,
            Permission.MEMBER_READ,
            Permission.PROJECT_READ,
            Permission.WORKSPACE_READ,
            Permission.WORKSPACE_WRITE,
            Permission.AGENT_READ,
            Permission.WORKFLOW_READ,
            Permission.ARTIFACT_READ,
            Permission.ARTIFACT_WRITE,
            Permission.APPROVAL_READ
    )),
    
    VIEWER(Set.of(
            Permission.ORGANIZATION_READ,
            Permission.MEMBER_READ,
            Permission.PROJECT_READ,
            Permission.WORKSPACE_READ,
            Permission.AGENT_READ,
            Permission.WORKFLOW_READ,
            Permission.ARTIFACT_READ,
            Permission.APPROVAL_READ
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
