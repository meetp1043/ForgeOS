package com.forgeos.event.domain;

import java.util.List;

public interface EventBus {
    
    <T extends ForgeEvent> void publish(String topic, EventEnvelope<T> event);

    <T extends ForgeEvent> void publishBatch(String topic, List<EventEnvelope<T>> events);
}
