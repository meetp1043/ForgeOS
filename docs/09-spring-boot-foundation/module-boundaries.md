# Module Boundaries

The ForgeOS backend is divided into the following top-level Spring Modulith modules:

- `shared`: Global domain types (Base exceptions, AggregateRoot, DomainEvent). Cannot depend on any other module.
- `identity`: User identity, authentication, and tenant awareness.
- `organization`: Teams, roles, and organization structures.
- `project`: Project metadata and configurations.
- `agent`: Agent Definitions, Versions, and Policies.
- `workflow`: The DAG-based task orchestrator.
- `context`: Data aggregation and filtering for prompts.
- `memory`: Long-term fact persistence and vector storage integration.
- `model`: Abstract provider interfaces (the bridge to LLMs).
- `tool`: The registry and sandbox execution interface for external actions.
- `execution`: The runtime state machine for a specific agent attempt.
- `artifact`: Storage references for generated code, docs, and test reports.
- `approval`: Human and Manager approval gates for high-risk actions.
- `audit`: Immutable logging of system events.
- `observability`: Telemetry, metrics, and tracing boundaries.

## Boundary Enforcement
Spring Modulith ensures that classes in `com.forgeos.workflow` cannot bypass the API of `com.forgeos.agent` to directly access its internal database entities.
