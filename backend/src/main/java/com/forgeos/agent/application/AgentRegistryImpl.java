package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentDefinition;
import com.forgeos.agent.domain.AgentRole;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AgentRegistryImpl implements AgentRegistry {
    
    private final Map<AgentRole, AgentDefinition> registry = new ConcurrentHashMap<>();

    @Override
    public Optional<AgentDefinition> getAgentDefinition(AgentRole role) {
        return Optional.ofNullable(registry.get(role));
    }

    @Override
    public void register(AgentDefinition definition) {
        registry.put(definition.getRole(), definition);
    }
}
