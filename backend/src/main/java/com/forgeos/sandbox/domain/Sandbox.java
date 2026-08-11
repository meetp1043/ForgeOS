package com.forgeos.sandbox.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Sandbox {
    private UUID sandboxId;
    private UUID executionId;
    private String runtimeType;
    private String workspaceLocalPath;
    private String status;
    private OffsetDateTime createdAt;

    public UUID getSandboxId() { return sandboxId; }
    public void setSandboxId(UUID sandboxId) { this.sandboxId = sandboxId; }
    public UUID getExecutionId() { return executionId; }
    public void setExecutionId(UUID executionId) { this.executionId = executionId; }
    public String getRuntimeType() { return runtimeType; }
    public void setRuntimeType(String runtimeType) { this.runtimeType = runtimeType; }
    public String getWorkspaceLocalPath() { return workspaceLocalPath; }
    public void setWorkspaceLocalPath(String workspaceLocalPath) { this.workspaceLocalPath = workspaceLocalPath; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
