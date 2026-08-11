package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskExecution;
import com.forgeos.workflow.domain.TaskStatus;
import com.forgeos.workflow.domain.WorkflowExecution;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkflowSchedulerTests {

    @Test
    void testGetReadyTasks() {
        WorkflowScheduler scheduler = new WorkflowSchedulerImpl();
        
        WorkflowExecution workflow = new WorkflowExecution();
        
        TaskExecution task1 = new TaskExecution();
        task1.setId(UUID.randomUUID());
        task1.setStatus(TaskStatus.COMPLETED);

        TaskExecution task2 = new TaskExecution();
        task2.setId(UUID.randomUUID());
        task2.setStatus(TaskStatus.PENDING); // In full impl, this checks dependencies

        TaskExecution task3 = new TaskExecution();
        task3.setId(UUID.randomUUID());
        task3.setStatus(TaskStatus.QUEUED);

        List<TaskExecution> readyTasks = scheduler.getReadyTasks(workflow, Arrays.asList(task1, task2, task3));

        // Simplified implementation test
        assertEquals(1, readyTasks.size());
        assertEquals(task2.getId(), readyTasks.get(0).getId());
    }
}
