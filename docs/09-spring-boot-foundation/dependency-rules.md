# Dependency Rules

To maintain the Modular Monolith, strict dependency rules are enforced.

## Allowed Dependencies

- **Downward**: Business modules can depend on `shared`.
- **Event-Driven**: Instead of `Workflow` calling `AgentExecution.start()`, `Workflow` publishes a `TaskAssignedEvent`. `Execution` listens to this event.
- **Interface-Driven**: If `Execution` needs to call `Model`, it must depend on an interface in `Model`'s exported API package, not on the OpenAI implementation class.

## Prohibited Dependencies

- **Cyclic Dependencies**: `Agent` depends on `Execution`, `Execution` depends on `Agent`. (Blocked by Spring Modulith).
- **Cross-Module Infrastructure**: `Agent` directly importing a JPA Repository from `Memory`.
- **Dumping Ground**: Placing business logic in the `shared` module. `shared` is exclusively for pure domain primitives (e.g., `UserId` record, `DomainEvent` interface).
