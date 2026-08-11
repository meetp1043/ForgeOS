package com.forgeos.event.outbox;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OutboxPublisher {

    private static final int MAX_ATTEMPTS = 5;

    private final OutboxEventRepository outboxRepository;
    private final OutboxEventRelay outboxEventRelay; // Abstraction to route raw outbox payloads to Kafka

    public OutboxPublisher(OutboxEventRepository outboxRepository, OutboxEventRelay outboxEventRelay) {
        this.outboxRepository = outboxRepository;
        this.outboxEventRelay = outboxEventRelay;
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishPendingEvents() {
        List<OutboxEvent> pendingEvents = outboxRepository.findByStatus(OutboxEventStatus.PENDING);
        
        for (OutboxEvent event : pendingEvents) {
            try {
                event.setStatus(OutboxEventStatus.PUBLISHING);
                outboxEventRelay.relay(event);
                
                event.setStatus(OutboxEventStatus.PUBLISHED);
                event.setPublishedAt(Instant.now());
            } catch (Exception e) {
                event.incrementAttempts();
                event.setLastError(e.getMessage());
                
                if (event.getAttempts() >= MAX_ATTEMPTS) {
                    event.setStatus(OutboxEventStatus.DEAD_LETTER);
                } else {
                    event.setStatus(OutboxEventStatus.PENDING);
                }
            }
            outboxRepository.save(event);
        }
    }
}
