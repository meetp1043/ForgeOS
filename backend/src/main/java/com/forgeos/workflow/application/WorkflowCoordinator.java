package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.WorkflowDefinition;
import com.forgeos.workflow.domain.WorkflowExecution;
import java.util.UUID;

public interface WorkflowCoordinator {
    
    WorkflowExecution createExecution(UUID workflowDefinitionId, UUID tenantId, UUID projectId, String objective);

    void startExecution(UUID workflowExecutionId);

    void pauseExecution(UUID workflowExecutionId);
    
    void cancelExecution(UUID workflowExecutionId);
}
