package com.forgeos.team.application;

import com.forgeos.team.domain.AgentTeam;
import com.forgeos.team.domain.AgentTeamMember;
import com.forgeos.team.domain.TaskStatus;
import com.forgeos.team.domain.TeamTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskAssignmentEngine {

    public void assignReadyTasks(AgentTeam team, List<TeamTask> tasks) {
        for (TeamTask task : tasks) {
            if (task.getStatus() == TaskStatus.READY) {
                
                // Find available agent with the correct role
                Optional<AgentTeamMember> matchingAgent = team.getMembers().stream()
                        .filter(m -> m.getRole() == task.getRequiredRole())
                        .findFirst(); // In a real system, check workload/availability too

                if (matchingAgent.isPresent()) {
                    task.setAssignedAgentId(matchingAgent.get().getAgentId());
                    task.setStatus(TaskStatus.ASSIGNED);
                }
            }
        }
    }
}
