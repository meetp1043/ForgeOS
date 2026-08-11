package com.forgeos.agent.domain;

import java.util.UUID;

public class AgentCheckpoint {
    private UUID checkpointId;
    private UUID executionId;
    private ExecutionStatus state;
    private UUID planReference;

    public AgentCheckpoint(UUID checkpointId, UUID executionId, ExecutionStatus state, UUID planReference) {
        this.checkpointId = checkpointId;
        this.executionId = executionId;
        this.state = state;
        this.planReference = planReference;
    }

    public UUID getCheckpointId() { return checkpointId; }
    public UUID getExecutionId() { return executionId; }
    public ExecutionStatus getState() { return state; }
    public UUID getPlanReference() { return planReference; }
}
