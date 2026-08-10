# Messaging Strategy

**RabbitMQ** is the designated message broker for decoupling modules.

## Architecture
- Modules publish Domain Events (e.g., `ContextPackageReadyEvent`).
- Spring Modulith's Event Publication Registry is used to guarantee event delivery (Outbox pattern).
- RabbitMQ routes these events to interested modules (e.g., the `Execution` module listening for the context to be ready so it can resume the LLM).

This prevents long HTTP/RPC blocking calls between modules during heavy operations like vector embedding generation.
