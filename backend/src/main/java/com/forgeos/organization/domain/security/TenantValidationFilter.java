package com.forgeos.organization.domain.security;

import com.forgeos.identity.domain.security.SecurityUser;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipEntity;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class TenantValidationFilter extends OncePerRequestFilter {

    private final OrganizationMembershipRepository membershipRepository;

    public TenantValidationFilter(OrganizationMembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String tenantIdHeader = request.getHeader("X-Tenant-ID");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (tenantIdHeader != null && authentication != null && authentication.isAuthenticated()) {
            try {
                UUID tenantId = UUID.fromString(tenantIdHeader);
                SecurityUser user = (SecurityUser) authentication.getPrincipal();

                Optional<OrganizationMembershipEntity> membership = 
                        membershipRepository.findByOrganizationIdAndUserId(tenantId, user.getId());

                if (membership.isPresent()) {
                    TenantContextHolder.setTenantId(tenantId);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not belong to this tenant");
                    return;
                }
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tenant ID format");
                return;
            }
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}
