package com.forgeos.tool.domain;

import java.util.UUID;

public class CapabilityGrant {
    private UUID agentId;
    private String capability;
    private String scope; // e.g., "PROJECT", "SYSTEM"
    
    public CapabilityGrant(UUID agentId, String capability, String scope) {
        this.agentId = agentId;
        this.capability = capability;
        this.scope = scope;
    }

    public UUID getAgentId() { return agentId; }
    public String getCapability() { return capability; }
    public String getScope() { return scope; }
}
