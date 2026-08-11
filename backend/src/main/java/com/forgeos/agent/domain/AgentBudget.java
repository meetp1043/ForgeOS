package com.forgeos.agent.domain;

public class AgentBudget {
    private int maxTokens;
    private int maxToolCalls;
    private int usedTokens;
    private int usedToolCalls;

    public AgentBudget(int maxTokens, int maxToolCalls) {
        this.maxTokens = maxTokens;
        this.maxToolCalls = maxToolCalls;
        this.usedTokens = 0;
        this.usedToolCalls = 0;
    }

    public void consumeToken(int count) {
        this.usedTokens += count;
    }

    public void consumeToolCall() {
        this.usedToolCalls += 1;
    }

    public boolean isExhausted() {
        return usedTokens >= maxTokens || usedToolCalls >= maxToolCalls;
    }

    public int getUsedTokens() { return usedTokens; }
    public int getUsedToolCalls() { return usedToolCalls; }
}
