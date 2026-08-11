package com.forgeos.workflow.domain;

public enum FailurePolicy {
    FAIL_FAST,
    CONTINUE_INDEPENDENT,
    RETRY_THEN_FAIL,
    ESCALATE,
    PARTIAL_SUCCESS
}
