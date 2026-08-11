package com.forgeos.sandbox.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Execution {
    private UUID executionId;
    private UUID tenantId;
    private UUID workspaceId;
    private ExecutionCommand command;
    private ExecutionStatus status;
    private Integer exitCode;
    private OffsetDateTime startedAt;
    private OffsetDateTime completedAt;

    public UUID getExecutionId() { return executionId; }
    public void setExecutionId(UUID executionId) { this.executionId = executionId; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public UUID getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(UUID workspaceId) { this.workspaceId = workspaceId; }
    public ExecutionCommand getCommand() { return command; }
    public void setCommand(ExecutionCommand command) { this.command = command; }
    public ExecutionStatus getStatus() { return status; }
    public void setStatus(ExecutionStatus status) { this.status = status; }
    public Integer getExitCode() { return exitCode; }
    public void setExitCode(Integer exitCode) { this.exitCode = exitCode; }
    public OffsetDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(OffsetDateTime completedAt) { this.completedAt = completedAt; }
}
