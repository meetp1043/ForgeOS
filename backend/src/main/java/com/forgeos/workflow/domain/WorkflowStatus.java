package com.forgeos.workflow.domain;

public enum WorkflowStatus {
    DRAFT,
    VALIDATING,
    ACTIVE,
    PAUSED,
    DEPRECATED,
    RETIRED,
    DISABLED,
    CREATED,
    QUEUED,
    INITIALIZING,
    RUNNING,
    WAITING,
    WAITING_APPROVAL,
    WAITING_AGENT,
    COMPLETING,
    COMPLETED,
    PARTIALLY_COMPLETED,
    FAILED,
    CANCELLED,
    TIMED_OUT,
    ESCALATED
}
