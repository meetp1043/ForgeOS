# Workflow Lifecycle

A workflow progresses through a well-defined set of states from creation to terminal completion.

## States

| State | Description |
| :--- | :--- |
| `CREATED` | The workflow instance has been instantiated but not yet validated. |
| `VALIDATING` | The engine is checking the definition, inputs, permissions, and budget. |
| `READY` | Validation passed. The workflow is queued for execution. |
| `RUNNING` | At least one step is actively executing. |
| `WAITING` | All active steps are waiting for an asynchronous event (e.g., a CI build). |
| `WAITING_FOR_APPROVAL` | Execution is paused at a human approval gate. |
| `BLOCKED` | A dependency or external system is preventing progress. |
| `PAUSED` | A human administrator has explicitly paused the workflow. |
| `FAILING` | A step has failed and the engine is executing compensation or escalation logic. |
| `RECOVERING` | The engine is rehydrating state after a crash or restart. |
| `COMPLETED` | All steps finished successfully. Terminal state. |
| `FAILED` | The workflow could not be completed and all recovery options are exhausted. Terminal state. |
| `CANCELLED` | A human or system policy cancelled the workflow. Terminal state. |
| `EXPIRED` | The workflow exceeded its maximum allowed duration. Terminal state. |

## Valid Transitions

```
CREATED → VALIDATING
VALIDATING → READY | FAILED
READY → RUNNING
RUNNING → WAITING | WAITING_FOR_APPROVAL | BLOCKED | PAUSED | FAILING | COMPLETED
WAITING → RUNNING | FAILING | EXPIRED
WAITING_FOR_APPROVAL → RUNNING | CANCELLED | EXPIRED
BLOCKED → RUNNING | FAILING | CANCELLED
PAUSED → RUNNING | CANCELLED
FAILING → RECOVERING | FAILED | CANCELLED
RECOVERING → RUNNING | FAILED
```

## Invariants
- Terminal states (`COMPLETED`, `FAILED`, `CANCELLED`, `EXPIRED`) are irreversible.
- A workflow in `PAUSED` state must not start new steps.
- A workflow in `RECOVERING` must rehydrate from its last persisted checkpoint before resuming.
