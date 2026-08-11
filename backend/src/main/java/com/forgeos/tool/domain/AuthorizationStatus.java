package com.forgeos.tool.domain;

public enum AuthorizationStatus {
    ALLOW,
    DENY,
    REQUIRES_APPROVAL,
    RATE_LIMITED,
    BLOCKED
}
