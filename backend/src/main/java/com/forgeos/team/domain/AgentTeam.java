package com.forgeos.team.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgentTeam {
    private UUID teamId;
    private String name;
    private List<AgentTeamMember> members;

    public AgentTeam(UUID teamId, String name) {
        this.teamId = teamId;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public UUID getTeamId() { return teamId; }
    public String getName() { return name; }
    public List<AgentTeamMember> getMembers() { return members; }
    
    public void addMember(AgentTeamMember member) {
        this.members.add(member);
    }
}
