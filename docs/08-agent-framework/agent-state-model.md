# Agent State Model

The Agent Framework tracks state at two different levels: the static definition, and the runtime instance.

## Definition States (Agent Registry)
These states apply to the immutable `Agent Version`:
- **DRAFT**: Currently being prompted/configured; not available for routing.
- **ACTIVE**: Fully evaluated, secure, and available for dynamic selection.
- **DEPRECATED**: Available for existing long-running workflows, but no new tasks will be routed to it.
- **SUSPENDED**: Temporarily disabled (usually due to a discovered security flaw or extreme hallucination rate).
- **RETIRED**: Permanently removed from routing.

## Instance States (Runtime Execution)
These states apply to the temporary `Agent Instance`:
- **ASSIGNED**: Task received, initialization pending.
- **INITIALIZING**: Fetching context and policies.
- **RUNNING**: LLM inference is actively generating tokens.
- **WAITING**: Yielding CPU/Thread to other system processes.
- **WAITING_FOR_TOOL**: LLM is paused; waiting for a heavy tool (e.g., Maven build) to complete.
- **WAITING_FOR_APPROVAL**: LLM is paused; waiting for Human or Manager Agent to authorize a high-risk action.
- **BLOCKED**: Execution halted due to a missing dependency (e.g., waiting for another agent to finish an API).
- **PAUSED**: Explicitly halted by a human operator.
- **CANCELLING**: Gracefully winding down operations and cleaning up sandboxes.
- **COMPLETED**: Task finished and validated successfully.
- **FAILED**: Task aborted due to an unrecoverable error.
- **ESCALATED**: Task aborted and explicitly handed off to a superior role.
- **CANCELLED**: Task forcibly terminated before completion.

## State Transitions
Valid transitions must be strictly enforced. An agent cannot move from `FAILED` to `COMPLETED` without a full `RETRY` loop.
