package com.forgeos.model.domain;

import java.util.Set;

public class ProviderDefinition {
    private String providerId;
    private String name;
    private ProviderStatus status;
    private String endpoint;
    private Set<String> supportedModels;
    private Set<ModelCapability> capabilities;
    private int priority;

    public ProviderDefinition(String providerId, String name, ProviderStatus status, int priority) {
        this.providerId = providerId;
        this.name = name;
        this.status = status;
        this.priority = priority;
    }

    public String getProviderId() { return providerId; }
    public String getName() { return name; }
    public ProviderStatus getStatus() { return status; }
    public int getPriority() { return priority; }
    public void setStatus(ProviderStatus status) { this.status = status; }
}
