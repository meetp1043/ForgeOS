package com.forgeos.event.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeos.event.domain.EventBus;
import com.forgeos.event.domain.EventEnvelope;
import com.forgeos.event.domain.ForgeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaEventBus implements EventBus {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaEventBus(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T extends ForgeEvent> void publish(String topic, EventEnvelope<T> event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            // Use causationId or tenantId as key for partitioning if needed. 
            // For now, using correlationId as partitioning key guarantees ordering per workflow
            String key = event.getPayload().getCorrelationId() != null 
                    ? event.getPayload().getCorrelationId().toString() 
                    : event.getPayload().getEventId().toString();
                    
            kafkaTemplate.send(topic, key, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event envelope", e);
        }
    }

    @Override
    public <T extends ForgeEvent> void publishBatch(String topic, List<EventEnvelope<T>> events) {
        for (EventEnvelope<T> event : events) {
            publish(topic, event);
        }
    }
}
