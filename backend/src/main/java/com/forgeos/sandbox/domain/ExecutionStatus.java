package com.forgeos.sandbox.domain;

public enum ExecutionStatus {
    QUEUED,
    STARTING,
    RUNNING,
    COMPLETED,
    FAILED,
    TIMED_OUT,
    CANCELLED,
    REJECTED,
    KILLED,
    RESOURCE_LIMIT_EXCEEDED,
    SECURITY_BLOCKED
}
