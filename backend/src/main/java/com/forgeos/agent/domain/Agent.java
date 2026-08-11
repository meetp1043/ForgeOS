package com.forgeos.agent.domain;

import java.util.UUID;

public class Agent {
    private UUID agentId;
    private String name;
    private String agentType; // e.g., BACKEND_DEVELOPER
    private String version;

    public Agent(UUID agentId, String name, String agentType, String version) {
        this.agentId = agentId;
        this.name = name;
        this.agentType = agentType;
        this.version = version;
    }

    public UUID getAgentId() { return agentId; }
    public String getName() { return name; }
    public String getAgentType() { return agentType; }
    public String getVersion() { return version; }
}
