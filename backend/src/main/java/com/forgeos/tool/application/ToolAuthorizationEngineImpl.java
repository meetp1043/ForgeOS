package com.forgeos.tool.application;

import com.forgeos.tool.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ToolAuthorizationEngineImpl implements ToolAuthorizationEngine {

    private final ToolRegistry registry;
    private final CapabilityResolver capabilityResolver;
    private final Map<UUID, ToolApproval> approvals = new ConcurrentHashMap<>();

    public ToolAuthorizationEngineImpl(ToolRegistry registry, CapabilityResolver capabilityResolver) {
        this.registry = registry;
        this.capabilityResolver = capabilityResolver;
    }

    @Override
    public void registerApproval(ToolApproval approval) {
        approvals.put(approval.getRequestId(), approval);
    }

    @Override
    public AuthorizationDecision evaluate(ToolRequest request) {
        // 1. Tool Exists?
        Optional<Tool> toolOpt = registry.getTool(request.getToolId());
        if (toolOpt.isEmpty()) {
            return new AuthorizationDecision(AuthorizationStatus.DENY, "Tool not found in registry.");
        }
        Tool tool = toolOpt.get();

        // 2. Has Required Capabilities?
        List<CapabilityGrant> agentGrants = capabilityResolver.getCapabilities(request.getAgentId());
        List<String> agentCapabilityNames = agentGrants.stream()
                .map(CapabilityGrant::getCapability)
                .collect(Collectors.toList());

        for (String reqCap : tool.getRequiredCapabilities()) {
            if (!agentCapabilityNames.contains(reqCap)) {
                return new AuthorizationDecision(AuthorizationStatus.DENY, "Agent lacks required capability: " + reqCap);
            }
        }

        // 3. Risk & Approval Check
        if (tool.getRiskLevel() == ToolRisk.HIGH || tool.getRiskLevel() == ToolRisk.CRITICAL) {
            ToolApproval approval = approvals.get(request.getRequestId());
            if (approval == null || !approval.isApproved()) {
                return new AuthorizationDecision(AuthorizationStatus.REQUIRES_APPROVAL, "Tool is high risk. Human approval required.");
            }
        }

        return new AuthorizationDecision(AuthorizationStatus.ALLOW, "Request authorized.");
    }
}
