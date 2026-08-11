package com.forgeos.tools.domain;

public class ToolDefinition {
    private String id;
    private String name;
    private String description;
    private String version;
    private ToolCategory category;
    private ToolRiskLevel riskLevel;
    private ToolPermission requiredPermission;
    private Long timeoutMs;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public ToolCategory getCategory() { return category; }
    public void setCategory(ToolCategory category) { this.category = category; }

    public ToolRiskLevel getRiskLevel() { return riskLevel; }
    public void setRiskLevel(ToolRiskLevel riskLevel) { this.riskLevel = riskLevel; }

    public ToolPermission getRequiredPermission() { return requiredPermission; }
    public void setRequiredPermission(ToolPermission requiredPermission) { this.requiredPermission = requiredPermission; }

    public Long getTimeoutMs() { return timeoutMs; }
    public void setTimeoutMs(Long timeoutMs) { this.timeoutMs = timeoutMs; }
}
