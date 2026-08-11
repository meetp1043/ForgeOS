package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskExecution;
import java.util.UUID;

public interface TaskWorker {
    /**
     * Claims a task atomically in the database to prevent duplicate execution.
     */
    boolean claimTask(UUID taskExecutionId);

    /**
     * Executes the task by invoking Agent Runtime, Human approval, or Gates.
     */
    void executeTask(TaskExecution task);
}
