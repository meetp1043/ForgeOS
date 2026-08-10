# Workflow Cancellation

Cancellation terminates a workflow before its natural completion.

## Cancellation Types

### Requested Cancellation
A human explicitly requests cancellation via the dashboard. The engine begins a graceful shutdown.

### Graceful Cancellation
1. The workflow status transitions to `CANCELLED`.
2. No new steps are started.
3. In-flight agent steps receive a cancellation signal. They are given a short grace period to save partial work.
4. In-flight tool executions in the Sandbox are terminated (SIGTERM, then SIGKILL after grace period).
5. Compensation logic is triggered for any steps that already completed successfully.
6. Cleanup tasks (e.g., deleting temporary branches, tearing down staging environments) are executed.

### Forced Cancellation
Used in emergencies (e.g., detected security breach, runaway cost).
1. The workflow status transitions to `CANCELLED` immediately.
2. All in-flight steps are killed without a grace period.
3. Compensation is attempted on a best-effort basis.
4. The incident is logged as a critical audit event.

## Post-Cancellation
- A cancelled workflow is a terminal state. It cannot be resumed.
- All artifacts produced by the workflow remain in the project workspace for human review.
- The audit log records the cancellation reason, the user who initiated it, and the state at the time of cancellation.
