package com.forgeos.tool.domain;

import java.time.OffsetDateTime;

public class AuthorizationDecision {
    private AuthorizationStatus decision;
    private String reason;
    private OffsetDateTime timestamp;

    public AuthorizationDecision(AuthorizationStatus decision, String reason) {
        this.decision = decision;
        this.reason = reason;
        this.timestamp = OffsetDateTime.now();
    }

    public AuthorizationStatus getDecision() { return decision; }
    public String getReason() { return reason; }
    public OffsetDateTime getTimestamp() { return timestamp; }
}
