package com.forgeos.team.domain;

import java.util.UUID;

public class AgentTeamMember {
    private UUID agentId;
    private UUID teamId;
    private AgentRole role;

    public AgentTeamMember(UUID agentId, UUID teamId, AgentRole role) {
        this.agentId = agentId;
        this.teamId = teamId;
        this.role = role;
    }

    public UUID getAgentId() { return agentId; }
    public UUID getTeamId() { return teamId; }
    public AgentRole getRole() { return role; }
}
