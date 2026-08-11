package com.forgeos.event.domain;

import java.time.Instant;
import java.util.UUID;

public interface ForgeEvent {
    UUID getEventId();
    String getEventType();
    String getEventVersion();
    Instant getOccurredAt();
    String getSource();
    UUID getTenantId();
    UUID getCorrelationId();
    UUID getCausationId();
}
