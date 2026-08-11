package com.forgeos.event.domain;

import java.time.Instant;
import java.util.UUID;

public class EventEnvelope<T extends ForgeEvent> {

    private T payload;
    private Instant publishedAt;
    private UUID traceId;
    private UUID spanId;
    
    // Default constructor for Jackson/serialization
    public EventEnvelope() {}

    public EventEnvelope(T payload, Instant publishedAt, UUID traceId, UUID spanId) {
        this.payload = payload;
        this.publishedAt = publishedAt;
        this.traceId = traceId;
        this.spanId = spanId;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }

    public UUID getSpanId() {
        return spanId;
    }

    public void setSpanId(UUID spanId) {
        this.spanId = spanId;
    }
}
