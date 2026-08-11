package com.forgeos.agent.domain;

import java.util.Map;
import java.util.UUID;

public class PlanStep {
    private UUID stepId;
    private int sequence;
    private String objective;
    private String tool;
    private Map<String, Object> inputs;
    private String expectedOutcome;
    private String status; // e.g., PENDING, COMPLETED, FAILED

    public PlanStep(UUID stepId, int sequence, String objective, String tool, Map<String, Object> inputs, String expectedOutcome) {
        this.stepId = stepId;
        this.sequence = sequence;
        this.objective = objective;
        this.tool = tool;
        this.inputs = inputs;
        this.expectedOutcome = expectedOutcome;
        this.status = "PENDING";
    }

    public UUID getStepId() { return stepId; }
    public int getSequence() { return sequence; }
    public String getObjective() { return objective; }
    public String getTool() { return tool; }
    public Map<String, Object> getInputs() { return inputs; }
    public String getExpectedOutcome() { return expectedOutcome; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
