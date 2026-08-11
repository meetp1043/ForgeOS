# 001 - Use Kafka for Event Bus

## Context
ForgeOS is transitioning from a single-agent architecture to a robust, distributed multi-agent system. This requires a production-ready asynchronous communication backbone (Phase 23) that strictly separates Commands from Events.

The system needs to support:
- High throughput and scalability for numerous agents and tools.
- Strict partitioning and ordering guarantees (e.g., all events for a specific workflow must be ordered).
- Durable event retention to support replay, auditing, and debugging.
- Multiple consumer groups (e.g., Audit Consumer, QA Consumer) processing the same event independently at their own pace.

Previously, `spring-boot-starter-amqp` (RabbitMQ) was present in the codebase. However, RabbitMQ's primary strength is in flexible routing and task queues rather than durable event streaming, partitioned ordering, and scalable replay.

## Decision
We will remove RabbitMQ dependencies and use **Apache Kafka** as the default production event backbone for ForgeOS.

## Consequences
- **Pros**:
  - Native support for consumer groups and distributed partitioned logs.
  - Excellent durability and retention semantics out-of-the-box (events are not deleted when consumed, enabling replay and audit trails).
  - High throughput capabilities matching the scale of AI agent execution.
- **Cons**:
  - Higher operational complexity compared to RabbitMQ.
  - Requires Kafka infrastructure for local development, which we will mitigate by providing a `docker-compose.yml`.
- **Implementation**:
  - `spring-boot-starter-amqp` will be removed.
  - `spring-kafka` will be introduced.
  - Kafka Testcontainers will be used for integration testing.
