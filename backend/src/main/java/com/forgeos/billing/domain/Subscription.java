package com.forgeos.billing.domain;

import java.time.Instant;
import java.util.UUID;

public record Subscription(
        UUID subscriptionId,
        UUID tenantId,
        UUID planId,
        SubscriptionStatus status,
        Instant startDate,
        Instant currentPeriodStart,
        Instant currentPeriodEnd,
        Instant cancelAt,
        Instant cancelledAt,
        String provider,
        String providerSubscriptionId
) {
    public enum SubscriptionStatus {
        TRIALING, ACTIVE, PAST_DUE, PAUSED, CANCELLED, EXPIRED
    }
}
