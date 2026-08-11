package com.forgeos.workflow.domain;

public enum TaskStatus {
    PENDING,
    READY,
    QUEUED,
    RUNNING,
    WAITING,
    WAITING_APPROVAL,
    WAITING_AGENT,
    COMPLETED,
    FAILED,
    BLOCKED,
    CANCELLED,
    SKIPPED,
    TIMED_OUT
}
