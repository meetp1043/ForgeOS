# Workflow Approval Gates

Approval gates are explicit checkpoints where workflow execution pauses and waits for a human decision.

## Approval Step Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `approval_id` | UUID | Unique identifier for this approval request. |
| `requester` | String | The agent or step that triggered the approval. |
| `approver_role` | String | The human role required to approve (e.g., `PROJECT_ADMIN`). |
| `risk_level` | Enum | `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`. |
| `description` | String | What is being approved and why. |
| `artifacts` | List | References to artifacts under review (e.g., PR link, migration script). |
| `expiration` | Duration | Maximum time to wait for a decision (e.g., 48 hours). |
| `decision` | Enum (nullable) | `APPROVED`, `REJECTED`, `CHANGES_REQUESTED`. |
| `reason` | String (nullable) | Justification for the decision. |
| `decided_by` | UUID (nullable) | The user who made the decision. |
| `decided_at` | Timestamp (nullable) | When the decision was made. |

## Standard Approval Gates
- **Requirements Approval**: Human confirms the PRD before architecture begins.
- **Architecture Approval**: Human confirms the system design before implementation.
- **Database Migration Approval**: Human confirms the schema change before execution.
- **Security Approval**: Human confirms security audit results before deployment.
- **Production Deployment Approval**: Human explicitly authorizes the production release.

## Expiration
If an approval request expires without a decision, the workflow transitions based on the configured timeout behavior: either `CANCELLED`, `ESCALATED`, or auto-approved (only for `LOW` risk).
