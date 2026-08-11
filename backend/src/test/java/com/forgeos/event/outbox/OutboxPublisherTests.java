package com.forgeos.event.outbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OutboxPublisherTests {

    private OutboxEventRepository repository;
    private OutboxEventRelay relay;
    private OutboxPublisher publisher;

    @BeforeEach
    void setUp() {
        repository = mock(OutboxEventRepository.class);
        relay = mock(OutboxEventRelay.class);
        publisher = new OutboxPublisher(repository, relay);
    }

    @Test
    void publishPendingEvents_SuccessfullyPublishes() {
        OutboxEvent event = new OutboxEvent(UUID.randomUUID(), "Agent", UUID.randomUUID().toString(), "agent.started", "v1", "{}");
        when(repository.findByStatus(OutboxEventStatus.PENDING)).thenReturn(List.of(event));

        publisher.publishPendingEvents();

        verify(relay).relay(event);
        verify(repository).save(event);
        assertEquals(OutboxEventStatus.PUBLISHED, event.getStatus());
        assertNotNull(event.getPublishedAt());
    }

    @Test
    void publishPendingEvents_FailureIncrementsAttemptsAndStaysPending() {
        OutboxEvent event = new OutboxEvent(UUID.randomUUID(), "Agent", UUID.randomUUID().toString(), "agent.started", "v1", "{}");
        when(repository.findByStatus(OutboxEventStatus.PENDING)).thenReturn(List.of(event));
        
        doThrow(new RuntimeException("Kafka down")).when(relay).relay(event);

        publisher.publishPendingEvents();

        assertEquals(OutboxEventStatus.PENDING, event.getStatus());
        assertEquals(1, event.getAttempts());
        assertEquals("Kafka down", event.getLastError());
    }

    @Test
    void publishPendingEvents_MaxAttemptsGoesToDLQ() {
        OutboxEvent event = new OutboxEvent(UUID.randomUUID(), "Agent", UUID.randomUUID().toString(), "agent.started", "v1", "{}");
        // Simulate already having 4 attempts
        for (int i = 0; i < 4; i++) {
            event.incrementAttempts();
        }
        
        when(repository.findByStatus(OutboxEventStatus.PENDING)).thenReturn(List.of(event));
        doThrow(new RuntimeException("Kafka still down")).when(relay).relay(event);

        publisher.publishPendingEvents();

        assertEquals(OutboxEventStatus.DEAD_LETTER, event.getStatus());
        assertEquals(5, event.getAttempts());
    }
}
