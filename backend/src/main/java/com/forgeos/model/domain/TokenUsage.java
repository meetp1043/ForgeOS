package com.forgeos.model.domain;

public class TokenUsage {
    private int inputTokens;
    private int outputTokens;
    private int cachedTokens;
    private int totalTokens;

    public TokenUsage(int inputTokens, int outputTokens, int cachedTokens) {
        this.inputTokens = inputTokens;
        this.outputTokens = outputTokens;
        this.cachedTokens = cachedTokens;
        this.totalTokens = inputTokens + outputTokens;
    }

    public int getInputTokens() { return inputTokens; }
    public int getOutputTokens() { return outputTokens; }
    public int getCachedTokens() { return cachedTokens; }
    public int getTotalTokens() { return totalTokens; }
}
