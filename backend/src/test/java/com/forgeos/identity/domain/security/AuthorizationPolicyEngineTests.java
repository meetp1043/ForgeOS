package com.forgeos.identity.domain.security;

import com.forgeos.identity.domain.security.policy.AuthorizationDecision;
import com.forgeos.identity.domain.security.policy.AuthorizationPolicyEngine;
import com.forgeos.identity.domain.security.policy.ForgeSecurityContext;
import com.forgeos.identity.domain.security.policy.ProjectScopedResource;
import com.forgeos.identity.domain.security.policy.TenantScopedResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationPolicyEngineTests {

    private AuthorizationPolicyEngine policyEngine;
    private ForgeSecurityContext developerContext;
    private ForgeSecurityContext viewerContext;

    @BeforeEach
    void setUp() {
        policyEngine = new AuthorizationPolicyEngine();
        UUID tenantId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        developerContext = new ForgeSecurityContext(null, tenantId, projectId, Role.DEVELOPER);
        viewerContext = new ForgeSecurityContext(null, tenantId, projectId, Role.VIEWER);
    }

    @Test
    void testDeveloperCanWriteToProjectResource() {
        ProjectScopedResource resource = () -> developerContext.getProjectId();
        AuthorizationDecision decision = policyEngine.authorize(developerContext, Permission.PROJECT_UPDATE, resource);
        assertEquals(AuthorizationDecision.ALLOW, decision);
    }

    @Test
    void testViewerCannotWriteToProjectResource() {
        ProjectScopedResource resource = () -> viewerContext.getProjectId();
        AuthorizationDecision decision = policyEngine.authorize(viewerContext, Permission.PROJECT_UPDATE, resource);
        assertEquals(AuthorizationDecision.DENY, decision);
    }

    @Test
    void testCrossProjectAccessDenied() {
        ProjectScopedResource wrongProject = UUID::randomUUID;
        AuthorizationDecision decision = policyEngine.authorize(developerContext, Permission.PROJECT_READ, wrongProject);
        assertEquals(AuthorizationDecision.DENY, decision); // Denied because of cross-project
    }

    @Test
    void testCrossTenantAccessDenied() {
        TenantScopedResource wrongTenant = UUID::randomUUID;
        AuthorizationDecision decision = policyEngine.authorize(developerContext, Permission.PROJECT_READ, wrongTenant);
        assertEquals(AuthorizationDecision.DENY, decision); // Denied because of cross-tenant
    }
}
