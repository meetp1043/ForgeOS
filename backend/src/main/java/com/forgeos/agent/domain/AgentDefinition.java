package com.forgeos.agent.domain;

import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.tools.domain.ToolPermission;
import com.forgeos.tools.domain.ToolRiskLevel;

import java.util.Set;
import java.util.UUID;

public class AgentDefinition {
    private UUID id;
    private AgentRole role;
    private String name;
    private String description;
    private String version;
    private AgentStatus status;
    private ToolRiskLevel maxRiskLevel;
    private Set<AgentCapability> capabilities;
    private Set<ToolPermission> allowedToolPermissions;
    private ModelPolicy modelPolicy;
    private boolean allowsDelegation;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public AgentRole getRole() { return role; }
    public void setRole(AgentRole role) { this.role = role; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public AgentStatus getStatus() { return status; }
    public void setStatus(AgentStatus status) { this.status = status; }
    public ToolRiskLevel getMaxRiskLevel() { return maxRiskLevel; }
    public void setMaxRiskLevel(ToolRiskLevel maxRiskLevel) { this.maxRiskLevel = maxRiskLevel; }
    public Set<AgentCapability> getCapabilities() { return capabilities; }
    public void setCapabilities(Set<AgentCapability> capabilities) { this.capabilities = capabilities; }
    public Set<ToolPermission> getAllowedToolPermissions() { return allowedToolPermissions; }
    public void setAllowedToolPermissions(Set<ToolPermission> allowedToolPermissions) { this.allowedToolPermissions = allowedToolPermissions; }
    public ModelPolicy getModelPolicy() { return modelPolicy; }
    public void setModelPolicy(ModelPolicy modelPolicy) { this.modelPolicy = modelPolicy; }
    public boolean isAllowsDelegation() { return allowsDelegation; }
    public void setAllowsDelegation(boolean allowsDelegation) { this.allowsDelegation = allowsDelegation; }
}
