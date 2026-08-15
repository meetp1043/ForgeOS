package com.forgeos.billing.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record Plan(
        UUID planId,
        String name,
        String description,
        PlanStatus status,
        String currency,
        BillingInterval billingInterval,
        BigDecimal basePrice,
        int trialDays,
        Map<String, Long> entitlements,
        Instant createdAt,
        Instant updatedAt
) {
    public enum PlanStatus {
        DRAFT, ACTIVE, ARCHIVED
    }

    public enum BillingInterval {
        MONTHLY, YEARLY
    }
}
