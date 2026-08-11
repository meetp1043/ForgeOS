package com.forgeos.workflow.domain;

import com.forgeos.agent.domain.AgentRole;
import java.util.List;
import java.util.UUID;

public class TaskDefinition {
    private UUID id;
    private UUID workflowDefinitionId;
    private String name;
    private TaskType type;
    private AgentRole agentRole;
    private List<UUID> dependencies;
    private FailurePolicy failurePolicy;
    private TaskPriority priority;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWorkflowDefinitionId() { return workflowDefinitionId; }
    public void setWorkflowDefinitionId(UUID workflowDefinitionId) { this.workflowDefinitionId = workflowDefinitionId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }
    public AgentRole getAgentRole() { return agentRole; }
    public void setAgentRole(AgentRole agentRole) { this.agentRole = agentRole; }
    public List<UUID> getDependencies() { return dependencies; }
    public void setDependencies(List<UUID> dependencies) { this.dependencies = dependencies; }
    public FailurePolicy getFailurePolicy() { return failurePolicy; }
    public void setFailurePolicy(FailurePolicy failurePolicy) { this.failurePolicy = failurePolicy; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
}
