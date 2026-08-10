# Testing Strategy

ForgeOS testing is layered to ensure reliability without excessive boilerplate.

## 1. Unit Tests
- Fast, isolated tests for domain logic.
- Mocking is permitted.

## 2. Architecture Tests
- `ModulithTests.java` uses Spring Modulith to verify that no circular dependencies exist and that package boundaries are respected.

## 3. Integration Tests
- **Testcontainers** are used to spin up real PostgreSQL, Redis, and RabbitMQ instances in Docker during the test phase.
- This ensures we test against real infrastructure without forcing developers to install these services locally.

## 4. Contract Tests (Future)
- For verifying LLM API schemas and frontend/backend REST contracts.
