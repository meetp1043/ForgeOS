package com.forgeos.identity.domain.security.policy;

import com.forgeos.identity.domain.security.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationPolicyEngine {

    public AuthorizationDecision authorize(ForgeSecurityContext context, Permission requiredPermission, Object resource) {
        if (context == null || context.getRole() == null) {
            return AuthorizationDecision.DENY;
        }

        List<Permission> grantedPermissions = getPermissionsForRole(context.getRole());
        
        if (!grantedPermissions.contains(requiredPermission)) {
            return AuthorizationDecision.DENY;
        }

        // Basic ABAC logic (e.g. resource must belong to the same project)
        if (resource instanceof ProjectScopedResource) {
            ProjectScopedResource projectResource = (ProjectScopedResource) resource;
            if (!projectResource.getProjectId().equals(context.getProjectId())) {
                return AuthorizationDecision.DENY; // Cross-project access denied
            }
        }
        
        if (resource instanceof TenantScopedResource) {
            TenantScopedResource tenantResource = (TenantScopedResource) resource;
            if (!tenantResource.getTenantId().equals(context.getTenantId())) {
                return AuthorizationDecision.DENY; // Cross-tenant access denied
            }
        }

        return AuthorizationDecision.ALLOW;
    }

    private List<Permission> getPermissionsForRole(com.forgeos.identity.domain.security.Role role) {
        switch (role) {
            case DEVELOPER:
                return List.of(Permission.PROJECT_READ, Permission.PROJECT_UPDATE, Permission.REPOSITORY_READ, Permission.REPOSITORY_WRITE, Permission.MODEL_USE);
            case VIEWER:
                return List.of(Permission.PROJECT_READ, Permission.REPOSITORY_READ);
            case SUPER_ADMIN:
                return List.of(Permission.values());
            default:
                return List.of();
        }
    }
}
