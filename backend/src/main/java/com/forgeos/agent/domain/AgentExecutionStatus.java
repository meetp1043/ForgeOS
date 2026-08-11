package com.forgeos.agent.domain;

public enum AgentExecutionStatus {
    CREATED,
    QUEUED,
    INITIALIZING,
    PLANNING,
    RUNNING,
    WAITING_FOR_TOOL,
    WAITING_FOR_APPROVAL,
    WAITING_FOR_AGENT,
    PAUSED,
    COMPLETING,
    COMPLETED,
    FAILED,
    CANCELLED,
    TIMED_OUT,
    ESCALATED
}
