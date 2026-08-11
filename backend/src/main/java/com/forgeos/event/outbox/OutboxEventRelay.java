package com.forgeos.event.outbox;

public interface OutboxEventRelay {
    /**
     * Relays the serialized OutboxEvent to the target message broker (e.g., Kafka).
     */
    void relay(OutboxEvent event);
}
