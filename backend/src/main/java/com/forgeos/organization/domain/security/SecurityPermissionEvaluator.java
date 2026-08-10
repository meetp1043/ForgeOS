package com.forgeos.organization.domain.security;

import com.forgeos.identity.domain.security.SecurityUser;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipEntity;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipRepository;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Component("securityPermissionEvaluator")
public class SecurityPermissionEvaluator implements PermissionEvaluator {

    private final OrganizationMembershipRepository membershipRepository;

    public SecurityPermissionEvaluator(OrganizationMembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        
        if (targetDomainObject instanceof UUID tenantId) {
            return checkTenantPermission(authentication, tenantId, (String) permission);
        }
        
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ((authentication == null) || (targetId == null) || !(permission instanceof String)) {
            return false;
        }

        if ("Tenant".equals(targetType) && targetId instanceof UUID tenantId) {
            return checkTenantPermission(authentication, tenantId, (String) permission);
        }

        return false;
    }

    private boolean checkTenantPermission(Authentication authentication, UUID tenantId, String permissionString) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        Optional<OrganizationMembershipEntity> membership =
                membershipRepository.findByOrganizationIdAndUserId(tenantId, user.getId());

        if (membership.isEmpty()) {
            return false; // Not a member
        }

        try {
            Role role = Role.valueOf(membership.get().getRoleId());
            Permission requiredPermission = Permission.valueOf(permissionString);
            return role.getPermissions().contains(requiredPermission);
        } catch (IllegalArgumentException e) {
            return false; // Unknown role or permission
        }
    }
}
