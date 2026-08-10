# Agent Cancellation

Cancellation is the process of aborting an in-flight Agent Execution before it naturally completes.

## Types of Cancellation

1. **Requested (Graceful) Cancellation**: A human or Workflow Engine requests the agent to stop. The Framework signals the agent, allowing it to finish its current tool, revert its `git` workspace, and emit a final status.
2. **Forced Cancellation (SIGKILL)**: An immediate, hard termination. Used when an agent hits a hard timeout, or when a security violation (e.g., sandbox escape) is detected.

## Cleanup
Regardless of the cancellation type, the Framework is responsible for cleanup:
- Destroying the ephemeral tool sandbox.
- Wiping the temporary workspace directory.
- Releasing any held database locks.

## Audit and State
A cancelled execution must transition to `CANCELLED`. It **must not** silently continue in a zombie thread, and it must not output a `SUCCESS` result. The audit log records who initiated the cancellation and why.
