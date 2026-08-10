# Locking Strategy

Optimistic locking using `@Version` in Spring Data JPA will be introduced in future phases on highly concurrent entities, such as:
1. `agent_executions`: When multiple workflows or agents attempt to update the same execution status.
2. `tasks`: When a workflow engine processes parallel nodes.

We do not preemptively add `@Version` columns in Phase 10 to keep the base schema simple until the exact concurrency requirements of the Workflow Engine are defined.
