# Workflow Cost Control

Cost control prevents runaway spending on AI tokens and cloud resources.

## Cost Policies

| Policy | Description | Default |
| :--- | :--- | :--- |
| **Max AI Budget** | Maximum total AI token spend for a workflow. | Configured per tenant/project. |
| **Max Execution Time** | Maximum wall-clock time for the entire workflow. | 7 days. |
| **Max Retries (Global)** | Maximum total retries across all steps in a workflow. | 50. |
| **Max Cloud Spend** | Maximum cloud infrastructure cost (if provisioning is part of the workflow). | Configured per tenant/project. |
| **Max Token Usage per Step** | Maximum tokens an individual agent step can consume. | Configured per step type. |
| **Warning Threshold** | Percentage of budget at which a `BudgetWarning` event is emitted. | 80%. |

## Enforcement
1. Before each step dispatch, the engine checks the accumulated cost against the budget.
2. If the warning threshold is crossed, a `BudgetWarning` event is emitted (notification to human).
3. If the hard budget is exceeded, the workflow behavior depends on the configured policy:
   - **PAUSE**: Workflow transitions to `PAUSED` and awaits human decision.
   - **ESCALATE**: Workflow continues but an escalation event is raised.
   - **TERMINATE**: Workflow transitions to `CANCELLED` immediately.

## Cost Tracking
The Billing Module aggregates `StepCompleted` events and calculates running costs by multiplying token counts by the model's pricing profile. This data is available in real-time via the dashboard.
