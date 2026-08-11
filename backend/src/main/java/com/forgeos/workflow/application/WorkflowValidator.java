package com.forgeos.workflow.application;

import com.forgeos.workflow.domain.WorkflowDefinition;
import java.util.List;

public interface WorkflowValidator {
    /**
     * Validates the Directed Acyclic Graph (DAG) for cycles, orphans, and unreachable tasks.
     * @param definition The workflow to validate.
     * @return List of validation error messages. Empty list if valid.
     */
    List<String> validate(WorkflowDefinition definition);
}
