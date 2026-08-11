package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskExecution;
import com.forgeos.workflow.domain.WorkflowExecution;
import java.util.List;

public interface WorkflowScheduler {
    
    /**
     * Determines which tasks are ready to run based on completed dependencies.
     */
    List<TaskExecution> getReadyTasks(WorkflowExecution execution, List<TaskExecution> allTasks);
    
    /**
     * Attempts to queue the ready tasks to workers.
     */
    void scheduleReadyTasks(WorkflowExecution execution);
}
