package com.forgeos.agent.domain;

import java.util.List;
import java.util.UUID;

public class AgentPlan {
    private UUID planId;
    private UUID executionId;
    private String goal;
    private List<PlanStep> steps;
    private int version;

    public AgentPlan(UUID planId, UUID executionId, String goal, List<PlanStep> steps, int version) {
        this.planId = planId;
        this.executionId = executionId;
        this.goal = goal;
        this.steps = steps;
        this.version = version;
    }

    public UUID getPlanId() { return planId; }
    public UUID getExecutionId() { return executionId; }
    public String getGoal() { return goal; }
    public List<PlanStep> getSteps() { return steps; }
    public int getVersion() { return version; }
}
