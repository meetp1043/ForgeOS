package com.forgeos.billing.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Invoice(
        UUID invoiceId,
        UUID tenantId,
        String billingPeriod,
        BigDecimal subtotal,
        BigDecimal tax,
        BigDecimal discount,
        BigDecimal total,
        String currency,
        InvoiceStatus status,
        String providerInvoiceId,
        Instant createdAt,
        Instant dueAt,
        Instant paidAt
) {
    public enum InvoiceStatus {
        DRAFT, OPEN, PAID, VOID, UNCOLLECTIBLE, FAILED
    }
}
