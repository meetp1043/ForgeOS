package com.forgeos.workflow.domain;

import java.util.UUID;

public class TaskExecution {
    private UUID id;
    private UUID workflowExecutionId;
    private UUID taskDefinitionId;
    private UUID agentExecutionId;
    private TaskStatus status;
    private String errorMessage;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWorkflowExecutionId() { return workflowExecutionId; }
    public void setWorkflowExecutionId(UUID workflowExecutionId) { this.workflowExecutionId = workflowExecutionId; }
    public UUID getTaskDefinitionId() { return taskDefinitionId; }
    public void setTaskDefinitionId(UUID taskDefinitionId) { this.taskDefinitionId = taskDefinitionId; }
    public UUID getAgentExecutionId() { return agentExecutionId; }
    public void setAgentExecutionId(UUID agentExecutionId) { this.agentExecutionId = agentExecutionId; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
