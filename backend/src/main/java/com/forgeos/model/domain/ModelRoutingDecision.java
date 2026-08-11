package com.forgeos.model.domain;

import java.time.Instant;
import java.util.List;

public class ModelRoutingDecision {
    private String selectedProviderId;
    private String selectedModelId;
    private String reason;
    private List<String> fallbackProviderIds;
    private Instant timestamp;

    public ModelRoutingDecision(String selectedProviderId, String selectedModelId, String reason, List<String> fallbackProviderIds) {
        this.selectedProviderId = selectedProviderId;
        this.selectedModelId = selectedModelId;
        this.reason = reason;
        this.fallbackProviderIds = fallbackProviderIds;
        this.timestamp = Instant.now();
    }

    public String getSelectedProviderId() { return selectedProviderId; }
    public String getSelectedModelId() { return selectedModelId; }
    public String getReason() { return reason; }
    public List<String> getFallbackProviderIds() { return fallbackProviderIds; }
}
