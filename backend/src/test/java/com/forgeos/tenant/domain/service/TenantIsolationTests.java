package com.forgeos.tenant.domain.service;

import com.forgeos.identity.domain.security.SecurityUser;
import com.forgeos.organization.domain.security.TenantValidationFilter;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipEntity;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipRepository;
import com.forgeos.tenant.infrastructure.persistence.TenantEntity;
import com.forgeos.tenant.infrastructure.persistence.TenantRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class TenantIsolationTests {

    private TenantValidationFilter filter;
    private OrganizationMembershipRepository membershipRepository;
    private TenantRepository tenantRepository;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        membershipRepository = mock(OrganizationMembershipRepository.class);
        tenantRepository = mock(TenantRepository.class);
        filter = new TenantValidationFilter(membershipRepository, tenantRepository);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        SecurityContextHolder.clearContext();
    }

    @Test
    void testAccessDeniedIfTenantDoesNotExistOrIsSuspended() throws Exception {
        UUID tenantId = UUID.randomUUID();
        when(request.getHeader("X-Tenant-ID")).thenReturn(tenantId.toString());

        SecurityUser user = new SecurityUser();
        user.setId(UUID.randomUUID());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));

        TenantEntity suspendedTenant = new TenantEntity();
        suspendedTenant.setStatus("SUSPENDED");
        when(tenantRepository.findById(tenantId)).thenReturn(Optional.of(suspendedTenant));

        filter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN, "Tenant does not exist or is inactive");
        verifyNoInteractions(filterChain);
    }

    @Test
    void testAccessDeniedIfUserLacksMembership() throws Exception {
        UUID tenantId = UUID.randomUUID();
        when(request.getHeader("X-Tenant-ID")).thenReturn(tenantId.toString());

        SecurityUser user = new SecurityUser();
        user.setId(UUID.randomUUID());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));

        TenantEntity activeTenant = new TenantEntity();
        activeTenant.setStatus("ACTIVE");
        when(tenantRepository.findById(tenantId)).thenReturn(Optional.of(activeTenant));

        when(membershipRepository.findByOrganizationIdAndUserId(tenantId, user.getId())).thenReturn(Optional.empty());

        filter.doFilter(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN, "User does not belong to this tenant");
        verifyNoInteractions(filterChain);
    }

    @Test
    void testAccessGrantedIfUserIsMember() throws Exception {
        UUID tenantId = UUID.randomUUID();
        when(request.getHeader("X-Tenant-ID")).thenReturn(tenantId.toString());

        SecurityUser user = new SecurityUser();
        user.setId(UUID.randomUUID());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));

        TenantEntity activeTenant = new TenantEntity();
        activeTenant.setStatus("ACTIVE");
        when(tenantRepository.findById(tenantId)).thenReturn(Optional.of(activeTenant));

        when(membershipRepository.findByOrganizationIdAndUserId(tenantId, user.getId())).thenReturn(Optional.of(new OrganizationMembershipEntity()));

        filter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}
