# Event Bus & Distributed Communication (Phase 23)

The Event Bus layer establishes a production-grade asynchronous communication backbone for ForgeOS, enabling loose coupling, robust auditing, and reliable event retention. It rigorously adheres to the principle of **Events as Immutable Facts**.

## Broker Infrastructure
We have selected **Apache Kafka** as the event broker because ForgeOS demands:
- **Durable Retention**: Events must be stored securely for audit and replay purposes.
- **Consumer Groups**: Independent services (e.g. Audit, UI updates, Workflow Orchestration) must process the same events asynchronously without interfering with one another.
- **Strict Ordering via Partitioning**: Events belonging to the same workflow execution are partitioned by their `correlationId`, guaranteeing strict sequential processing for a given execution.

## Transactional Outbox
To prevent the "Dual Write" problem (where a DB transaction commits but the Kafka publish fails), we use the **Transactional Outbox Pattern**:
1. Business logic updates state and saves an `OutboxEvent` (status = `PENDING`) in the same database transaction.
2. The `OutboxPublisher` polls the outbox and publishes the event to Kafka.
3. If successful, the event is marked `PUBLISHED`.
4. If it fails transiently, it increments an attempts counter. After 5 failures, it is moved to a `DEAD_LETTER` state.

## Core Abstractions
- **`ForgeEvent`**: Defines required metadata (eventId, version, timestamps, causationId, correlationId, tenantId).
- **`EventEnvelope`**: Wraps the payload with tracing data (`traceId`, `spanId`) and publish timestamps, providing a clean separation between routing/tracing metadata and business logic.
- **`EventBus`**: The core API used by the application, with `KafkaEventBus` as the backing implementation.
