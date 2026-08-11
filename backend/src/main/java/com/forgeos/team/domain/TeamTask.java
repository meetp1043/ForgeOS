package com.forgeos.team.domain;

import java.util.UUID;

public class TeamTask {
    private UUID taskId;
    private UUID teamId;
    private String title;
    private AgentRole requiredRole;
    private TaskStatus status;
    private UUID assignedAgentId;

    public TeamTask(UUID taskId, UUID teamId, String title, AgentRole requiredRole) {
        this.taskId = taskId;
        this.teamId = teamId;
        this.title = title;
        this.requiredRole = requiredRole;
        this.status = TaskStatus.BACKLOG;
    }

    public UUID getTaskId() { return taskId; }
    public UUID getTeamId() { return teamId; }
    public String getTitle() { return title; }
    public AgentRole getRequiredRole() { return requiredRole; }
    public TaskStatus getStatus() { return status; }
    public UUID getAssignedAgentId() { return assignedAgentId; }

    public void setStatus(TaskStatus status) { this.status = status; }
    public void setAssignedAgentId(UUID assignedAgentId) { this.assignedAgentId = assignedAgentId; }
}
