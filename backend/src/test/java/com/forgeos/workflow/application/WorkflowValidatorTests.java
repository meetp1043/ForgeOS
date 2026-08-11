package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskDefinition;
import com.forgeos.workflow.domain.WorkflowDefinition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkflowValidatorTests {

    @Test
    void testCycleDetection() {
        WorkflowValidator validator = new WorkflowValidatorImpl();
        
        WorkflowDefinition workflow = new WorkflowDefinition();
        
        TaskDefinition task1 = new TaskDefinition();
        task1.setId(UUID.randomUUID());
        task1.setName("Task 1");

        TaskDefinition task2 = new TaskDefinition();
        task2.setId(UUID.randomUUID());
        task2.setName("Task 2");

        TaskDefinition task3 = new TaskDefinition();
        task3.setId(UUID.randomUUID());
        task3.setName("Task 3");

        // Create cycle: Task 1 -> Task 2 -> Task 3 -> Task 1
        task1.setDependencies(Collections.singletonList(task2.getId()));
        task2.setDependencies(Collections.singletonList(task3.getId()));
        task3.setDependencies(Collections.singletonList(task1.getId()));

        workflow.setTasks(Arrays.asList(task1, task2, task3));

        List<String> errors = validator.validate(workflow);
        assertFalse(errors.isEmpty(), "Cycle should be detected");
    }

    @Test
    void testValidGraph() {
        WorkflowValidator validator = new WorkflowValidatorImpl();
        
        WorkflowDefinition workflow = new WorkflowDefinition();
        
        TaskDefinition task1 = new TaskDefinition();
        task1.setId(UUID.randomUUID());
        task1.setName("Requirements");

        TaskDefinition task2 = new TaskDefinition();
        task2.setId(UUID.randomUUID());
        task2.setName("Backend");
        task2.setDependencies(Collections.singletonList(task1.getId()));

        TaskDefinition task3 = new TaskDefinition();
        task3.setId(UUID.randomUUID());
        task3.setName("Frontend");
        task3.setDependencies(Collections.singletonList(task1.getId()));

        TaskDefinition task4 = new TaskDefinition();
        task4.setId(UUID.randomUUID());
        task4.setName("Integration");
        task4.setDependencies(Arrays.asList(task2.getId(), task3.getId()));

        workflow.setTasks(Arrays.asList(task1, task2, task3, task4));

        List<String> errors = validator.validate(workflow);
        assertTrue(errors.isEmpty(), "Graph should be valid");
    }
}
