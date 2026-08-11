package com.forgeos.tool.domain;

import java.util.List;

public class Tool {
    private String toolId;
    private String version;
    private String category;
    private ToolRisk riskLevel;
    private List<String> requiredCapabilities;
    
    public Tool(String toolId, String version, String category, ToolRisk riskLevel, List<String> requiredCapabilities) {
        this.toolId = toolId;
        this.version = version;
        this.category = category;
        this.riskLevel = riskLevel;
        this.requiredCapabilities = requiredCapabilities;
    }

    public String getToolId() { return toolId; }
    public String getVersion() { return version; }
    public String getCategory() { return category; }
    public ToolRisk getRiskLevel() { return riskLevel; }
    public List<String> getRequiredCapabilities() { return requiredCapabilities; }
}
