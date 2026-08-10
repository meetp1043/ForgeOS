package com.forgeos.agent.infrastructure.persistence;

import com.forgeos.agent.domain.AgentStatus;
import com.forgeos.shared.infrastructure.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "agent_versions")
public class AgentVersionEntity extends BaseEntity {

    @Column(name = "agent_definition_id", nullable = false)
    private UUID agentDefinitionId;

    @Column(name = "version_string", nullable = false)
    private String versionString;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgentStatus status;

    @Column(name = "configuration_ref")
    private String configurationRef;

    @Column(name = "activated_at")
    private Instant activatedAt;

    @Column(name = "retired_at")
    private Instant retiredAt;

    public UUID getAgentDefinitionId() {
        return agentDefinitionId;
    }

    public void setAgentDefinitionId(UUID agentDefinitionId) {
        this.agentDefinitionId = agentDefinitionId;
    }

    public String getVersionString() {
        return versionString;
    }

    public void setVersionString(String versionString) {
        this.versionString = versionString;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }

    public String getConfigurationRef() {
        return configurationRef;
    }

    public void setConfigurationRef(String configurationRef) {
        this.configurationRef = configurationRef;
    }

    public Instant getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Instant activatedAt) {
        this.activatedAt = activatedAt;
    }

    public Instant getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Instant retiredAt) {
        this.retiredAt = retiredAt;
    }
}
