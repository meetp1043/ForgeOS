package com.forgeos.team.application;

import com.forgeos.team.domain.TaskDependency;
import com.forgeos.team.domain.TaskStatus;
import com.forgeos.team.domain.TeamTask;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DagTaskScheduler {

    public void evaluateTasks(List<TeamTask> allTasks, List<TaskDependency> dependencies) {
        validateNoCycles(allTasks, dependencies);

        Map<UUID, List<UUID>> prerequisitesMap = buildPrerequisitesMap(allTasks, dependencies);

        for (TeamTask task : allTasks) {
            if (task.getStatus() == TaskStatus.BACKLOG || task.getStatus() == TaskStatus.BLOCKED) {
                boolean allPrereqsCompleted = true;
                List<UUID> prereqs = prerequisitesMap.getOrDefault(task.getTaskId(), List.of());
                
                for (UUID prereqId : prereqs) {
                    TeamTask prereqTask = allTasks.stream()
                            .filter(t -> t.getTaskId().equals(prereqId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Prerequisite task not found"));

                    if (prereqTask.getStatus() != TaskStatus.COMPLETED) {
                        allPrereqsCompleted = false;
                        break;
                    }
                }

                if (allPrereqsCompleted) {
                    task.setStatus(TaskStatus.READY);
                } else {
                    task.setStatus(TaskStatus.BLOCKED);
                }
            }
        }
    }

    private void validateNoCycles(List<TeamTask> tasks, List<TaskDependency> dependencies) {
        Map<UUID, List<UUID>> adj = new HashMap<>();
        for (TeamTask task : tasks) {
            adj.put(task.getTaskId(), new ArrayList<>());
        }
        for (TaskDependency dep : dependencies) {
            // Edge from prerequisite to dependent (to find cycles in the "must complete before" flow)
            adj.get(dep.getPrerequisiteTaskId()).add(dep.getDependentTaskId());
        }

        Set<UUID> visited = new HashSet<>();
        Set<UUID> recursionStack = new HashSet<>();

        for (TeamTask task : tasks) {
            if (hasCycle(task.getTaskId(), adj, visited, recursionStack)) {
                throw new IllegalStateException("Circular dependency detected in tasks");
            }
        }
    }

    private boolean hasCycle(UUID node, Map<UUID, List<UUID>> adj, Set<UUID> visited, Set<UUID> recStack) {
        if (recStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        for (UUID neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, visited, recStack)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }

    private Map<UUID, List<UUID>> buildPrerequisitesMap(List<TeamTask> tasks, List<TaskDependency> dependencies) {
        Map<UUID, List<UUID>> map = new HashMap<>();
        for (TaskDependency dep : dependencies) {
            map.computeIfAbsent(dep.getDependentTaskId(), k -> new ArrayList<>()).add(dep.getPrerequisiteTaskId());
        }
        return map;
    }
}
