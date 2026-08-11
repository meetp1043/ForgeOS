package com.forgeos.tools.domain;

public enum ToolStatus {
    REQUESTED,
    AUTHORIZED,
    WAITING_APPROVAL,
    RUNNING,
    COMPLETED,
    FAILED,
    TIMED_OUT,
    CANCELLED,
    DENIED
}
