package com.forgeos.tool.domain;

import java.util.Map;
import java.util.UUID;

public class ToolRequest {
    private UUID requestId;
    private UUID agentId;
    private String toolId;
    private Map<String, Object> arguments;

    public ToolRequest(UUID requestId, UUID agentId, String toolId, Map<String, Object> arguments) {
        this.requestId = requestId;
        this.agentId = agentId;
        this.toolId = toolId;
        this.arguments = arguments;
    }

    public UUID getRequestId() { return requestId; }
    public UUID getAgentId() { return agentId; }
    public String getToolId() { return toolId; }
    public Map<String, Object> getArguments() { return arguments; }
}
