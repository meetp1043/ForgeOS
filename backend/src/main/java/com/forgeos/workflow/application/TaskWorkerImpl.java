package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.TaskExecution;
import com.forgeos.workflow.domain.TaskStatus;
import com.forgeos.agent.application.AgentSupervisor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskWorkerImpl implements TaskWorker {

    private final AgentSupervisor agentSupervisor;

    public TaskWorkerImpl(AgentSupervisor agentSupervisor) {
        this.agentSupervisor = agentSupervisor;
    }

    @Override
    public boolean claimTask(UUID taskExecutionId) {
        // Should execute an atomic UPDATE task_executions SET status='RUNNING' WHERE id=? AND status='QUEUED'
        return true;
    }

    @Override
    public void executeTask(TaskExecution task) {
        task.setStatus(TaskStatus.RUNNING);
        try {
            // Integration with Agent Runtime (Phase 14)
            // Example: agentSupervisor.createExecution(task.getAgentRole(), ...)
            
            // Wait for agent execution result
            
            task.setStatus(TaskStatus.COMPLETED);
        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setErrorMessage(e.getMessage());
        }
    }
}
