package com.forgeos.execution.domain;

public enum ExecutionStatus {
    ASSIGNED,
    INITIALIZING,
    RUNNING,
    WAITING_FOR_TOOL,
    WAITING_FOR_APPROVAL,
    COMPLETED,
    FAILED,
    ESCALATED,
    CANCELLED
}
