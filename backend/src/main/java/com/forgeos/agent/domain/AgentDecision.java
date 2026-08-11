package com.forgeos.agent.domain;

import java.util.Map;

public class AgentDecision {
    private AgentDecisionType type;
    private String targetToolOrAgent;
    private Map<String, Object> arguments;
    private String reasoning;
    private String content;

    public AgentDecisionType getType() { return type; }
    public void setType(AgentDecisionType type) { this.type = type; }
    public String getTargetToolOrAgent() { return targetToolOrAgent; }
    public void setTargetToolOrAgent(String targetToolOrAgent) { this.targetToolOrAgent = targetToolOrAgent; }
    public Map<String, Object> getArguments() { return arguments; }
    public void setArguments(Map<String, Object> arguments) { this.arguments = arguments; }
    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
