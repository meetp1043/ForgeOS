package com.forgeos.model.domain;

import java.time.Instant;
import java.util.Map;

public class ModelResponse {
    private String content;
    private Map<String, Object> structuredOutput;
    private String provider;
    private String model;
    private String finishReason;
    private TokenUsage tokenUsage;
    private Long latencyMs;
    private String requestId;
    private boolean cached;
    private boolean fallbackUsed;
    private Instant createdAt;

    public ModelResponse() {
        this.createdAt = Instant.now();
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Map<String, Object> getStructuredOutput() { return structuredOutput; }
    public void setStructuredOutput(Map<String, Object> structuredOutput) { this.structuredOutput = structuredOutput; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getFinishReason() { return finishReason; }
    public void setFinishReason(String finishReason) { this.finishReason = finishReason; }

    public TokenUsage getTokenUsage() { return tokenUsage; }
    public void setTokenUsage(TokenUsage tokenUsage) { this.tokenUsage = tokenUsage; }

    public Long getLatencyMs() { return latencyMs; }
    public void setLatencyMs(Long latencyMs) { this.latencyMs = latencyMs; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public boolean isCached() { return cached; }
    public void setCached(boolean cached) { this.cached = cached; }

    public boolean isFallbackUsed() { return fallbackUsed; }
    public void setFallbackUsed(boolean fallbackUsed) { this.fallbackUsed = fallbackUsed; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
