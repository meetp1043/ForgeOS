package com.forgeos.tool.application;

import com.forgeos.tool.domain.CapabilityGrant;
import java.util.List;
import java.util.UUID;

public interface CapabilityResolver {
    List<CapabilityGrant> getCapabilities(UUID agentId);
    void grantCapability(CapabilityGrant grant);
}
