package com.forgeos.tool.application;

import com.forgeos.tool.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToolAuthorizationEndToEndTests {

    private ToolRegistry registry;
    private CapabilityResolver capabilityResolver;
    private ToolAuthorizationEngine engine;

    @BeforeEach
    void setUp() {
        registry = new ToolRegistryImpl();
        capabilityResolver = new CapabilityResolverImpl();
        engine = new ToolAuthorizationEngineImpl(registry, capabilityResolver);
        
        // Setup Tools
        registry.registerTool(new Tool("git.diff", "v1", "GIT", ToolRisk.LOW, List.of("git.read")));
        registry.registerTool(new Tool("production.deploy", "v1", "DEPLOYMENT", ToolRisk.CRITICAL, List.of("deploy.production")));
        registry.registerTool(new Tool("github.merge", "v1", "GITHUB", ToolRisk.HIGH, List.of("github.merge")));
    }

    @Test
    void testLowRiskToolAllowedWhenCapabilitiesMatch() {
        UUID agentId = UUID.randomUUID();
        capabilityResolver.grantCapability(new CapabilityGrant(agentId, "git.read", "PROJECT"));
        
        ToolRequest req = new ToolRequest(UUID.randomUUID(), agentId, "git.diff", Collections.emptyMap());
        AuthorizationDecision decision = engine.evaluate(req);
        
        assertEquals(AuthorizationStatus.ALLOW, decision.getDecision());
    }

    @Test
    void testToolDeniedWhenCapabilityMissing() {
        UUID agentId = UUID.randomUUID();
        // Agent only has git.read, but wants to deploy
        capabilityResolver.grantCapability(new CapabilityGrant(agentId, "git.read", "PROJECT"));
        
        ToolRequest req = new ToolRequest(UUID.randomUUID(), agentId, "production.deploy", Collections.emptyMap());
        AuthorizationDecision decision = engine.evaluate(req);
        
        assertEquals(AuthorizationStatus.DENY, decision.getDecision());
        assertTrue(decision.getReason().contains("Agent lacks required capability"));
    }

    @Test
    void testHighRiskToolRequiresApproval() {
        UUID agentId = UUID.randomUUID();
        // Agent HAS capability, but tool is HIGH risk
        capabilityResolver.grantCapability(new CapabilityGrant(agentId, "github.merge", "PROJECT"));
        
        UUID requestId = UUID.randomUUID();
        ToolRequest req = new ToolRequest(requestId, agentId, "github.merge", Collections.emptyMap());
        
        // 1. Initial Evaluation -> Requires Approval
        AuthorizationDecision decision1 = engine.evaluate(req);
        assertEquals(AuthorizationStatus.REQUIRES_APPROVAL, decision1.getDecision());
        
        // 2. Human Approves
        engine.registerApproval(new ToolApproval(requestId, true));
        
        // 3. Second Evaluation -> Allowed
        AuthorizationDecision decision2 = engine.evaluate(req);
        assertEquals(AuthorizationStatus.ALLOW, decision2.getDecision());
    }
}
