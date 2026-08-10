# Workflow Concurrency

Concurrency control prevents conflicting operations from corrupting state.

## Concurrency Hazards

| Hazard | Description | Mitigation |
| :--- | :--- | :--- |
| **Duplicate Task Execution** | The same step is dispatched to two agents simultaneously. | Deduplication table + optimistic locking on step status. |
| **Conflicting File Modifications** | Two agents edit the same file concurrently. | Distributed locks (Redis) keyed by file path within a project. |
| **Duplicate Deployments** | Two workflow instances attempt to deploy the same service simultaneously. | Deployment lock per service per environment. |
| **Conflicting DB Operations** | Two migrations run concurrently on the same database. | Migration lock (single-writer) per target database. |
| **Race Condition on Approval** | Two humans attempt to approve the same gate simultaneously. | Optimistic locking on the approval record — first write wins. |

## Locking Strategy
- **Optimistic Locking**: Used for database records (workflow instances, step statuses). A `version` column prevents lost updates.
- **Distributed Locks**: Used for shared resources (files, deployment targets). Implemented via Redis with TTL-based automatic expiry to prevent deadlocks.
- **Ownership Model**: Each active step "owns" the resources it is modifying. The Orchestrator enforces that no two steps can own the same resource simultaneously.

## Deadlock Prevention
- Locks have a maximum TTL. If an agent crashes while holding a lock, the lock expires automatically.
- The engine does not support nested locks to avoid deadlock cycles.
