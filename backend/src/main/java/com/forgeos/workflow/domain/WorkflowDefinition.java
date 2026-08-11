package com.forgeos.workflow.domain;

import java.util.List;
import java.util.UUID;

public class WorkflowDefinition {
    private UUID id;
    private String name;
    private String description;
    private String version;
    private WorkflowStatus status;
    private UUID tenantId;
    private List<TaskDefinition> tasks;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public WorkflowStatus getStatus() { return status; }
    public void setStatus(WorkflowStatus status) { this.status = status; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public List<TaskDefinition> getTasks() { return tasks; }
    public void setTasks(List<TaskDefinition> tasks) { this.tasks = tasks; }
}
