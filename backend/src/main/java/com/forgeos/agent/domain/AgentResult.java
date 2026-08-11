package com.forgeos.agent.domain;

import java.util.List;
import java.util.UUID;

public class AgentResult {
    private UUID executionId;
    private AgentExecutionStatus status;
    private String summary;
    private List<String> artifacts;
    private List<String> evidence;
    private String errorMessage;

    public UUID getExecutionId() { return executionId; }
    public void setExecutionId(UUID executionId) { this.executionId = executionId; }
    public AgentExecutionStatus getStatus() { return status; }
    public void setStatus(AgentExecutionStatus status) { this.status = status; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<String> getArtifacts() { return artifacts; }
    public void setArtifacts(List<String> artifacts) { this.artifacts = artifacts; }
    public List<String> getEvidence() { return evidence; }
    public void setEvidence(List<String> evidence) { this.evidence = evidence; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
