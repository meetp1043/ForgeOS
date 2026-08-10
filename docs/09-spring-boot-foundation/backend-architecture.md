# Backend Architecture

ForgeOS backend is built on **Java 21** and **Spring Boot 3.x**.

## Why a Modular Monolith?

Given the complexity of an AI OS with Workflow, Memory, and Agent engines, starting with microservices would introduce excessive distributed systems complexity (network latency, distributed transactions, complex CI/CD) before domain boundaries are fully understood.

A Modular Monolith provides the logical separation of microservices (using Spring Modulith) with the deployment simplicity of a monolith. If the `Memory Engine` eventually requires massive independent scaling for vector search, it can be extracted into a microservice easily because its boundaries were enforced from day one.

## Core Technologies
- **Java 21**: Leveraging Virtual Threads (Loom) for high-concurrency agent execution without thread pool exhaustion.
- **Spring Boot 3.x**: Core application framework.
- **Spring Modulith**: Enforces architectural boundaries at compile-time.
- **PostgreSQL**: Primary relational data store for state and configuration.
- **Redis**: Fast caching, distributed locks, and temporary agent execution state.
- **RabbitMQ**: Asynchronous event bus for decoupling modules (e.g., Workflow emitting an `AgentAssignedEvent`).
