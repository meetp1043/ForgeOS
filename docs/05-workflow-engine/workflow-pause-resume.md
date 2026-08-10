# Workflow Pause / Resume

Pause and resume provide humans with direct control over long-running workflows.

## Pause Behavior
When a `PAUSE` command is issued:
1. The workflow status transitions to `PAUSED`.
2. **No new steps are started.** Steps currently in `READY` state remain `READY` but are not dispatched.
3. **In-flight steps continue to a safe stopping point.** An agent currently executing a tool call is allowed to receive the tool result and persist its state, but is not given the next tool call. The step transitions to `WAITING`.
4. **Approval gates remain open.** If a human has a pending approval, they can still approve/reject even while the workflow is paused.
5. **State is persisted.** The exact workflow state is committed to the database.

## Resume Behavior
When a `RESUME` command is issued:
1. The engine validates that the workflow definition version is still valid.
2. The workflow status transitions from `PAUSED` to `RUNNING`.
3. Steps that were `READY` are now dispatched.
4. Steps that were `WAITING` resume their execution.
5. All timers (step timeouts, workflow timeout) resume counting from where they were paused.

## Use Cases
- A human notices excessive token spend and pauses to investigate.
- A critical external system is undergoing maintenance; the workflow is paused until it returns.
- A human wants to manually review intermediate artifacts before allowing the workflow to continue.
