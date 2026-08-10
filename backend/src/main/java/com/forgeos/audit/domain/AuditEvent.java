package com.forgeos.audit.domain;

import java.time.Instant;

public record AuditEvent(String eventId, String eventType, String actorId, String resourceId, Instant timestamp, String payload) {}
