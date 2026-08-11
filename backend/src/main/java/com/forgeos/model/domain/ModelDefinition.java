package com.forgeos.model.domain;

import java.util.Set;

public class ModelDefinition {
    private String modelId;
    private String providerId;
    private String displayName;
    private String version;
    private Set<ModelCapability> capabilities;
    private int contextWindow;
    private int maxOutputTokens;
    private ModelStatus status;

    public ModelDefinition(String modelId, String providerId, ModelStatus status) {
        this.modelId = modelId;
        this.providerId = providerId;
        this.status = status;
    }

    public String getModelId() { return modelId; }
    public String getProviderId() { return providerId; }
    public ModelStatus getStatus() { return status; }
    public void setStatus(ModelStatus status) { this.status = status; }
}
