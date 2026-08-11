package com.forgeos.sandbox.domain;

import java.util.UUID;

public class ExecutionResult {
    private UUID executionId;
    private ExecutionStatus status;
    private Integer exitCode;
    private String stdoutReference;
    private String stderrReference;

    public UUID getExecutionId() { return executionId; }
    public void setExecutionId(UUID executionId) { this.executionId = executionId; }
    public ExecutionStatus getStatus() { return status; }
    public void setStatus(ExecutionStatus status) { this.status = status; }
    public Integer getExitCode() { return exitCode; }
    public void setExitCode(Integer exitCode) { this.exitCode = exitCode; }
    public String getStdoutReference() { return stdoutReference; }
    public void setStdoutReference(String stdoutReference) { this.stdoutReference = stdoutReference; }
    public String getStderrReference() { return stderrReference; }
    public void setStderrReference(String stderrReference) { this.stderrReference = stderrReference; }
}
