# Workflow State Model

The workflow state model defines the durable, persistent representation of a running workflow.

## State Persistence
All state transitions are committed to the relational database (PostgreSQL) **before** the corresponding action is taken. This write-ahead approach ensures crash recovery.

## State Components

1. **Workflow Status**: The current lifecycle state (see `workflow-lifecycle.md`).
2. **Step Statuses**: The individual status of every step in the workflow DAG.
3. **Execution Cursor**: Which steps are currently executing or eligible for execution.
4. **Context Snapshot**: A reference to the assembled context (not the full context itself).
5. **Event Log**: An append-only log of every state transition that has occurred.
6. **Checkpoint**: A serializable marker that allows the engine to resume from this exact point.

## Consistency Guarantees
- State mutations use database transactions to prevent partial writes.
- Concurrent updates to the same workflow instance are prevented via optimistic locking (version column).
- The event log is immutable — entries are appended, never modified or deleted.
