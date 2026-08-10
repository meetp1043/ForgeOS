# Workflow Events

The workflow engine emits domain events at every significant state transition. These events power observability, auditing, and integration with other ForgeOS modules.

## Event Catalog

| Event | Trigger |
| :--- | :--- |
| `WorkflowCreated` | A new workflow instance is created. |
| `WorkflowValidated` | The workflow passes validation and is ready to run. |
| `WorkflowStarted` | The first step begins execution. |
| `StepStarted` | An individual step begins execution. |
| `StepCompleted` | A step finishes successfully. |
| `StepFailed` | A step fails (after retries). |
| `StepRetried` | A step is retried after a transient failure. |
| `StepSkipped` | A step is skipped due to a condition or upstream failure. |
| `ApprovalRequested` | A step requires human approval. |
| `ApprovalGranted` | A human approves a pending request. |
| `ApprovalRejected` | A human rejects a pending request. |
| `WorkflowPaused` | A human pauses the workflow. |
| `WorkflowResumed` | A human resumes the workflow. |
| `WorkflowCancelled` | The workflow is cancelled. |
| `WorkflowEscalated` | An issue is escalated up the chain. |
| `WorkflowCompleted` | All steps finished successfully. |
| `WorkflowFailed` | The workflow failed and all recovery is exhausted. |
| `WorkflowExpired` | The workflow exceeded its maximum allowed duration. |
| `CompensationStarted` | Compensation logic begins for a failed workflow. |
| `CompensationCompleted` | Compensation completed successfully. |
| `BudgetWarning` | Token or cost usage has crossed a warning threshold. |
| `BudgetExceeded` | Token or cost budget is exhausted. |

## Event Schema
Each event contains: `event_id`, `event_type`, `workflow_instance_id`, `step_id` (if applicable), `timestamp`, `tenant_id`, `project_id`, `payload` (event-specific data).

## Consumers
- **Observability Module**: Consumes all events for metrics and tracing.
- **Notification Module**: Consumes `ApprovalRequested`, `WorkflowFailed`, `BudgetExceeded` to alert humans.
- **Audit Module**: Consumes all events for the immutable audit log.
- **Billing Module**: Consumes `StepCompleted` events containing token usage data.
