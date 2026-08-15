package com.forgeos.billing.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record UsageEvent(
        UUID usageEventId,
        UUID tenantId,
        String resourceType,
        String metricType,
        long quantity,
        String unit,
        Instant timestamp,
        String source,
        String idempotencyKey,
        Map<String, String> metadata
) {}
