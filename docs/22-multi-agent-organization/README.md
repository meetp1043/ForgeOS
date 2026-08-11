# Multi-Agent Organization & Collaboration (Phase 22)

The Multi-Agent Organization layer transforms ForgeOS into a software engineering team. It introduces the concepts of `AgentTeam`, Specialized Agents (e.g. `FRONTEND_DEVELOPER`, `SECURITY_ENGINEER`), `TeamTask` scheduling via DAGs (Directed Acyclic Graphs), and structured communication.

## Core Architecture

### Team Structure
An `AgentTeam` consists of multiple `AgentTeamMember`s. Each member has a specific `AgentRole`. Agents do not escalate their capabilities just because they join a team; they retain the strict policy controls implemented in earlier phases.

### Task Scheduling (DAG)
The `DagTaskScheduler` builds a dependency graph of all `TeamTask`s. 
- It prevents circular dependencies (e.g., Task A blocks B, but B blocks A).
- A task is marked as `READY` only when all of its prerequisites are `COMPLETED`. Until then, it remains `BLOCKED`.

### Task Assignment
The `TaskAssignmentEngine` scans for `READY` tasks and assigns them to available agents whose specialization matches the `requiredRole` of the task (e.g. a Frontend task is routed to the `FRONTEND_DEVELOPER`).

### Secure Communication
Agents collaborate via the `AgentMessageBroker`. This broker enforces team boundaries, ensuring that cross-tenant or unauthenticated communication attempts throw a `SecurityException`.
