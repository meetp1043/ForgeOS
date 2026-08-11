package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.WorkflowExecution;
import com.forgeos.workflow.domain.WorkflowStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkflowCoordinatorImpl implements WorkflowCoordinator {

    private final WorkflowScheduler scheduler;

    public WorkflowCoordinatorImpl(WorkflowScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public WorkflowExecution createExecution(UUID workflowDefinitionId, UUID tenantId, UUID projectId, String objective) {
        WorkflowExecution execution = new WorkflowExecution();
        execution.setId(UUID.randomUUID());
        execution.setWorkflowDefinitionId(workflowDefinitionId);
        execution.setTenantId(tenantId);
        execution.setProjectId(projectId);
        execution.setObjective(objective);
        execution.setStatus(WorkflowStatus.CREATED);
        return execution;
    }

    @Override
    public void startExecution(UUID workflowExecutionId) {
        // Query execution, set status to RUNNING, invoke scheduler
        WorkflowExecution mockExecution = new WorkflowExecution();
        mockExecution.setId(workflowExecutionId);
        mockExecution.setStatus(WorkflowStatus.RUNNING);
        scheduler.scheduleReadyTasks(mockExecution);
    }

    @Override
    public void pauseExecution(UUID workflowExecutionId) {
        // Update execution status to PAUSED
    }

    @Override
    public void cancelExecution(UUID workflowExecutionId) {
        // Update execution status to CANCELLED and broadcast event to workers
    }
}
