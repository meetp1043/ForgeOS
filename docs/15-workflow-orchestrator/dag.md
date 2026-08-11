# Directed Acyclic Graph (DAG)

All workflows in ForgeOS are modeled as a DAG.

## Validation
Before a `WorkflowDefinition` becomes `ACTIVE`, the `WorkflowValidator` guarantees:
1. No cycles exist (A -> B -> A).
2. No unreachable orphan tasks exist.
3. Every referenced `AgentRole` exists in the system.
4. Impossible dependencies (Task A depends on Task B, Task B depends on Task C, Task C depends on Task A) are rejected.

## Parallel Execution
The orchestrator natively supports parallelism. If a "Frontend" task and a "Backend" task both depend only on the "Architecture" task, they will automatically be `QUEUED` and executed simultaneously once the Architecture task hits `COMPLETED`.
