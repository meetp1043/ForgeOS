package com.forgeos.model.domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ModelRequest {
    private String systemInstruction;
    private List<String> userMessages;
    private String context;
    private Set<ModelCapability> requiredCapabilities;
    private Double temperature;
    private Integer maxOutputTokens;
    private ModelPolicy policy;
    private ModelPrivacyClassification privacyClassification;
    private UUID tenantId;
    private UUID projectId;
    private String correlationId;

    public ModelRequest() {
    }

    public String getSystemInstruction() {
        return systemInstruction;
    }

    public void setSystemInstruction(String systemInstruction) {
        this.systemInstruction = systemInstruction;
    }

    public List<String> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<String> userMessages) {
        this.userMessages = userMessages;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<ModelCapability> getRequiredCapabilities() {
        return requiredCapabilities;
    }

    public void setRequiredCapabilities(Set<ModelCapability> requiredCapabilities) {
        this.requiredCapabilities = requiredCapabilities;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxOutputTokens() {
        return maxOutputTokens;
    }

    public void setMaxOutputTokens(Integer maxOutputTokens) {
        this.maxOutputTokens = maxOutputTokens;
    }

    public ModelPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(ModelPolicy policy) {
        this.policy = policy;
    }

    public ModelPrivacyClassification getPrivacyClassification() {
        return privacyClassification;
    }

    public void setPrivacyClassification(ModelPrivacyClassification privacyClassification) {
        this.privacyClassification = privacyClassification;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
