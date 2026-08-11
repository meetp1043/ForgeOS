package com.forgeos.tools.domain;

import java.util.Map;

public class ToolResult {
    private String executionId;
    private ToolStatus status;
    private String output;
    private Map<String, Object> structuredResult;
    private Integer exitCode;
    private Long durationMs;
    private ToolErrorCode errorCode;
    private String errorMessage;

    public ToolResult() {
    }

    public static ToolResult success(String executionId, String output) {
        ToolResult result = new ToolResult();
        result.setExecutionId(executionId);
        result.setStatus(ToolStatus.COMPLETED);
        result.setOutput(output);
        result.setExitCode(0);
        return result;
    }

    public static ToolResult failure(String executionId, ToolErrorCode errorCode, String errorMessage) {
        ToolResult result = new ToolResult();
        result.setExecutionId(executionId);
        result.setStatus(ToolStatus.FAILED);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public String getExecutionId() { return executionId; }
    public void setExecutionId(String executionId) { this.executionId = executionId; }

    public ToolStatus getStatus() { return status; }
    public void setStatus(ToolStatus status) { this.status = status; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }

    public Map<String, Object> getStructuredResult() { return structuredResult; }
    public void setStructuredResult(Map<String, Object> structuredResult) { this.structuredResult = structuredResult; }

    public Integer getExitCode() { return exitCode; }
    public void setExitCode(Integer exitCode) { this.exitCode = exitCode; }

    public Long getDurationMs() { return durationMs; }
    public void setDurationMs(Long durationMs) { this.durationMs = durationMs; }

    public ToolErrorCode getErrorCode() { return errorCode; }
    public void setErrorCode(ToolErrorCode errorCode) { this.errorCode = errorCode; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
