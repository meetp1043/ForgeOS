package com.forgeos.event.outbox;

public enum OutboxEventStatus {
    PENDING,
    PUBLISHING,
    PUBLISHED,
    FAILED,
    DEAD_LETTER
}
