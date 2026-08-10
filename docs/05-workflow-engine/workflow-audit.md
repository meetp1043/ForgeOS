# Workflow Audit

Every significant workflow action is recorded in an immutable audit log.

## Auditable Actions
- Workflow creation, start, pause, resume, cancel, completion, failure.
- Step start, completion, failure, retry, skip.
- Approval request, grant, rejection.
- Human overrides and manual interventions.
- Escalation events.
- Compensation execution.
- Budget threshold crossings.

## Audit Record Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `audit_id` | UUID | Unique record identifier. |
| `actor_type` | Enum | `AGENT`, `HUMAN`, `SYSTEM`. |
| `actor_id` | UUID | The agent instance ID, user ID, or system component. |
| `workflow_instance_id` | UUID | The workflow this action belongs to. |
| `step_id` | UUID (nullable) | The specific step (if applicable). |
| `action` | String | What was done (e.g., "STEP_COMPLETED", "APPROVAL_GRANTED"). |
| `timestamp` | Timestamp | When the action occurred. |
| `input_ref` | UUID (nullable) | Reference to the input data used. |
| `output_ref` | UUID (nullable) | Reference to the output data produced. |
| `decision` | String (nullable) | The decision made (e.g., "APPROVED"). |
| `reason` | String (nullable) | Justification for the action. |
| `result` | String | Outcome (e.g., "SUCCESS", "FAILURE"). |

## Immutability
Audit records are append-only. They cannot be modified or deleted by any agent, user, or system component. This ensures forensic-grade traceability.

## Retention
Audit logs are retained for the lifetime of the project plus a configurable retention period (default: 1 year post-project-archive).
