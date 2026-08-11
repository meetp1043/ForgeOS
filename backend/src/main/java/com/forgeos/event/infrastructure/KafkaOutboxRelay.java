package com.forgeos.event.infrastructure;

import com.forgeos.event.outbox.OutboxEvent;
import com.forgeos.event.outbox.OutboxEventRelay;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOutboxRelay implements OutboxEventRelay {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOutboxRelay(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void relay(OutboxEvent event) {
        // The Outbox payload is typically already a serialized JSON string of the EventEnvelope
        // We route it to a topic based on the aggregate type
        String topic = "forgeos." + event.getAggregateType().toLowerCase() + ".events";
        String key = event.getAggregateId();

        // KafkaTemplate.send returns a CompletableFuture, we could call .get() for synchronous exception throwing
        // which helps the OutboxPublisher mark it as FAILED if the broker is down.
        try {
            kafkaTemplate.send(topic, key, event.getPayload()).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send outbox event to Kafka", e);
        }
    }
}
