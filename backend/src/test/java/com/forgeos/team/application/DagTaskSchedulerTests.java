package com.forgeos.team.application;

import com.forgeos.team.domain.AgentRole;
import com.forgeos.team.domain.TaskDependency;
import com.forgeos.team.domain.TaskStatus;
import com.forgeos.team.domain.TeamTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DagTaskSchedulerTests {

    private DagTaskScheduler scheduler;

    @BeforeEach
    void setUp() {
        scheduler = new DagTaskScheduler();
    }

    @Test
    void taskRemainsBlockedUntilPrerequisiteCompletes() {
        UUID teamId = UUID.randomUUID();
        TeamTask taskA = new TeamTask(UUID.randomUUID(), teamId, "Backend API", AgentRole.BACKEND_DEVELOPER);
        TeamTask taskB = new TeamTask(UUID.randomUUID(), teamId, "QA Tests", AgentRole.QA_ENGINEER);
        
        // B depends on A
        TaskDependency dep = new TaskDependency(taskB.getTaskId(), taskA.getTaskId());

        // A is in backlog
        scheduler.evaluateTasks(List.of(taskA, taskB), List.of(dep));
        assertEquals(TaskStatus.READY, taskA.getStatus());
        assertEquals(TaskStatus.BLOCKED, taskB.getStatus());

        // Complete A
        taskA.setStatus(TaskStatus.COMPLETED);
        scheduler.evaluateTasks(List.of(taskA, taskB), List.of(dep));
        
        assertEquals(TaskStatus.READY, taskB.getStatus());
    }

    @Test
    void circularDependencyThrowsException() {
        UUID teamId = UUID.randomUUID();
        TeamTask taskA = new TeamTask(UUID.randomUUID(), teamId, "Task A", AgentRole.BACKEND_DEVELOPER);
        TeamTask taskB = new TeamTask(UUID.randomUUID(), teamId, "Task B", AgentRole.FRONTEND_DEVELOPER);

        // A depends on B, B depends on A
        TaskDependency dep1 = new TaskDependency(taskA.getTaskId(), taskB.getTaskId());
        TaskDependency dep2 = new TaskDependency(taskB.getTaskId(), taskA.getTaskId());

        assertThrows(IllegalStateException.class, () -> {
            scheduler.evaluateTasks(List.of(taskA, taskB), List.of(dep1, dep2));
        });
    }
}
