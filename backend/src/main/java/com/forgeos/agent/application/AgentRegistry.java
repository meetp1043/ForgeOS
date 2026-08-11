package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentDefinition;
import com.forgeos.agent.domain.AgentRole;

import java.util.Optional;

public interface AgentRegistry {
    Optional<AgentDefinition> getAgentDefinition(AgentRole role);
    void register(AgentDefinition definition);
}
