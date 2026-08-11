package com.forgeos.tool.application;

import com.forgeos.tool.domain.CapabilityGrant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CapabilityResolverImpl implements CapabilityResolver {
    private final Map<UUID, List<CapabilityGrant>> grants = new ConcurrentHashMap<>();

    @Override
    public void grantCapability(CapabilityGrant grant) {
        grants.computeIfAbsent(grant.getAgentId(), k -> new ArrayList<>()).add(grant);
    }

    @Override
    public List<CapabilityGrant> getCapabilities(UUID agentId) {
        return grants.getOrDefault(agentId, List.of());
    }
}
