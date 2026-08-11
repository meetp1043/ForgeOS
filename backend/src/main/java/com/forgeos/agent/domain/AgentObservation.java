package com.forgeos.agent.domain;

import java.util.UUID;

public class AgentObservation {
    private UUID observationId;
    private UUID executionId;
    private boolean success;
    private String resultData;
    private String reasoning;

    public AgentObservation(UUID observationId, UUID executionId, boolean success, String resultData, String reasoning) {
        this.observationId = observationId;
        this.executionId = executionId;
        this.success = success;
        this.resultData = resultData;
        this.reasoning = reasoning;
    }

    public UUID getObservationId() { return observationId; }
    public UUID getExecutionId() { return executionId; }
    public boolean isSuccess() { return success; }
    public String getResultData() { return resultData; }
    public String getReasoning() { return reasoning; }
}
