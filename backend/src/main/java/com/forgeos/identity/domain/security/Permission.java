package com.forgeos.identity.domain.security;

public enum Permission {
    PROJECT_READ("project:read"),
    PROJECT_UPDATE("project:update"),
    AGENT_EXECUTE("agent:execute"),
    WORKFLOW_CREATE("workflow:create"),
    WORKFLOW_EXECUTE("workflow:execute"),
    TOOL_EXECUTE("tool:execute"),
    MODEL_USE("model:use"),
    REPOSITORY_READ("repository:read"),
    REPOSITORY_WRITE("repository:write"),
    SANDBOX_EXECUTE("sandbox:execute");

    private final String value;

    Permission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
