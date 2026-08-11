package com.forgeos.event.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeos.event.domain.EventEnvelope;
import com.forgeos.event.domain.ForgeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KafkaEventBusTests {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;
    private KafkaEventBus eventBus;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // Ensure Instant can be serialized
        eventBus = new KafkaEventBus(kafkaTemplate, objectMapper);
    }

    @Test
    void publish_SerializesAndSendsToKafka() throws Exception {
        UUID eventId = UUID.randomUUID();
        UUID correlationId = UUID.randomUUID();

        TestForgeEvent payload = new TestForgeEvent(eventId, correlationId);
        EventEnvelope<TestForgeEvent> envelope = new EventEnvelope<>(payload, Instant.now(), UUID.randomUUID(), UUID.randomUUID());

        eventBus.publish("test.topic", envelope);

        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);
        // correlationId is used as the key for partitioning
        verify(kafkaTemplate).send(eq("test.topic"), eq(correlationId.toString()), payloadCaptor.capture());
        
        String jsonPayload = payloadCaptor.getValue();
        EventEnvelope<TestForgeEvent> deserialized = objectMapper.readValue(jsonPayload, 
            objectMapper.getTypeFactory().constructParametricType(EventEnvelope.class, TestForgeEvent.class));
            
        assertEquals(eventId, deserialized.getPayload().getEventId());
    }

    // A simple mock event for testing
    public static class TestForgeEvent implements ForgeEvent {
        private UUID eventId;
        private UUID correlationId;

        public TestForgeEvent() {}

        public TestForgeEvent(UUID eventId, UUID correlationId) {
            this.eventId = eventId;
            this.correlationId = correlationId;
        }

        @Override public UUID getEventId() { return eventId; }
        @Override public String getEventType() { return "test.event"; }
        @Override public String getEventVersion() { return "v1"; }
        @Override public Instant getOccurredAt() { return Instant.now(); }
        @Override public String getSource() { return "test-source"; }
        @Override public UUID getTenantId() { return UUID.randomUUID(); }
        @Override public UUID getCorrelationId() { return correlationId; }
        @Override public UUID getCausationId() { return UUID.randomUUID(); }
    }
}
