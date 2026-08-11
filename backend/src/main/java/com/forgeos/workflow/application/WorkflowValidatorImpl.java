package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskDefinition;
import com.forgeos.workflow.domain.WorkflowDefinition;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkflowValidatorImpl implements WorkflowValidator {

    @Override
    public List<String> validate(WorkflowDefinition definition) {
        List<String> errors = new ArrayList<>();
        if (definition.getTasks() == null || definition.getTasks().isEmpty()) {
            errors.add("Workflow must contain at least one task.");
            return errors;
        }

        Map<UUID, TaskDefinition> taskMap = new HashMap<>();
        for (TaskDefinition task : definition.getTasks()) {
            taskMap.put(task.getId(), task);
        }

        // Cycle detection using depth-first search
        Set<UUID> visited = new HashSet<>();
        Set<UUID> recursionStack = new HashSet<>();

        for (TaskDefinition task : definition.getTasks()) {
            if (hasCycle(task, taskMap, visited, recursionStack)) {
                errors.add("Cycle detected involving task: " + task.getName());
                break; // Stop after finding the first cycle
            }
        }
        
        // Check for missing dependencies
        for (TaskDefinition task : definition.getTasks()) {
            if (task.getDependencies() != null) {
                for (UUID depId : task.getDependencies()) {
                    if (!taskMap.containsKey(depId)) {
                        errors.add("Task " + task.getName() + " depends on non-existent task ID: " + depId);
                    }
                }
            }
        }

        return errors;
    }

    private boolean hasCycle(TaskDefinition task, Map<UUID, TaskDefinition> taskMap, Set<UUID> visited, Set<UUID> recursionStack) {
        if (recursionStack.contains(task.getId())) return true;
        if (visited.contains(task.getId())) return false;

        visited.add(task.getId());
        recursionStack.add(task.getId());

        if (task.getDependencies() != null) {
            for (UUID depId : task.getDependencies()) {
                TaskDefinition depTask = taskMap.get(depId);
                if (depTask != null && hasCycle(depTask, taskMap, visited, recursionStack)) {
                    return true;
                }
            }
        }

        recursionStack.remove(task.getId());
        return false;
    }
}
