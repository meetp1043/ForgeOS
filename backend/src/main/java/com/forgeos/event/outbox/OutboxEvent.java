package com.forgeos.event.outbox;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_event")
public class OutboxEvent {
    
    @Id
    private UUID id;
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private String eventVersion;
    private String payload;
    
    @Enumerated(EnumType.STRING)
    private OutboxEventStatus status;
    private int attempts;
    private Instant createdAt;
    private Instant publishedAt;
    private String lastError;

    // Default constructor for JPA
    protected OutboxEvent() {}

    public OutboxEvent(UUID id, String aggregateType, String aggregateId, String eventType, String eventVersion, String payload) {
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.eventVersion = eventVersion;
        this.payload = payload;
        this.status = OutboxEventStatus.PENDING;
        this.attempts = 0;
        this.createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public String getAggregateType() { return aggregateType; }
    public String getAggregateId() { return aggregateId; }
    public String getEventType() { return eventType; }
    public String getEventVersion() { return eventVersion; }
    public String getPayload() { return payload; }
    
    public OutboxEventStatus getStatus() { return status; }
    public void setStatus(OutboxEventStatus status) { this.status = status; }

    public int getAttempts() { return attempts; }
    public void incrementAttempts() { this.attempts++; }

    public Instant getCreatedAt() { return createdAt; }
    
    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public String getLastError() { return lastError; }
    public void setLastError(String lastError) { this.lastError = lastError; }
}
