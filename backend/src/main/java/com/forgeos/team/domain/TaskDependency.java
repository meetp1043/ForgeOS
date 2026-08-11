package com.forgeos.team.domain;

import java.util.UUID;

public class TaskDependency {
    private UUID dependentTaskId; // Task that waits
    private UUID prerequisiteTaskId; // Task that must finish first

    public TaskDependency(UUID dependentTaskId, UUID prerequisiteTaskId) {
        this.dependentTaskId = dependentTaskId;
        this.prerequisiteTaskId = prerequisiteTaskId;
    }

    public UUID getDependentTaskId() { return dependentTaskId; }
    public UUID getPrerequisiteTaskId() { return prerequisiteTaskId; }
}
