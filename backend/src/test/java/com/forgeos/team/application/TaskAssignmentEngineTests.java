package com.forgeos.team.application;

import com.forgeos.team.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskAssignmentEngineTests {

    private TaskAssignmentEngine engine;

    @BeforeEach
    void setUp() {
        engine = new TaskAssignmentEngine();
    }

    @Test
    void assignReadyTaskToAvailableSpecializedAgent() {
        UUID teamId = UUID.randomUUID();
        AgentTeam team = new AgentTeam(teamId, "Engineering Team");
        
        UUID backendAgentId = UUID.randomUUID();
        team.addMember(new AgentTeamMember(backendAgentId, teamId, AgentRole.BACKEND_DEVELOPER));
        
        UUID frontendAgentId = UUID.randomUUID();
        team.addMember(new AgentTeamMember(frontendAgentId, teamId, AgentRole.FRONTEND_DEVELOPER));

        TeamTask task = new TeamTask(UUID.randomUUID(), teamId, "Build UI", AgentRole.FRONTEND_DEVELOPER);
        task.setStatus(TaskStatus.READY);

        engine.assignReadyTasks(team, List.of(task));

        assertEquals(TaskStatus.ASSIGNED, task.getStatus());
        assertEquals(frontendAgentId, task.getAssignedAgentId());
    }
}
