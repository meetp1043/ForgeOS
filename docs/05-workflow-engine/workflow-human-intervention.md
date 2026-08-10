# Workflow Human Intervention

Humans are the ultimate authority in ForgeOS workflows. The system provides multiple intervention mechanisms.

## Intervention Actions

| Action | Description | Effect on Workflow |
| :--- | :--- | :--- |
| **Approve** | Accept a pending approval request. | Workflow resumes from the approval gate. |
| **Reject** | Deny a pending approval request. | Workflow transitions via `ON_REJECTION`. May cancel or reroute. |
| **Request Changes** | Send feedback to the agent for revision. | The step is reassigned with the human's feedback injected into context. |
| **Pause** | Temporarily halt the workflow. | Workflow transitions to `PAUSED`. No new steps start. |
| **Resume** | Continue a paused workflow. | Workflow transitions to `RUNNING`. |
| **Cancel** | Permanently stop the workflow. | Workflow transitions to `CANCELLED`. Cleanup is triggered. |
| **Override** | Manually modify an artifact or decision. | The override is recorded, and downstream steps re-evaluate. |
| **Retry** | Re-execute a specific failed step. | The step is reset to `READY` and re-dispatched. |

## Guardrails
- All human actions are permanently recorded in the audit log with the user's identity, timestamp, and reason.
- A human cannot bypass a `CRITICAL` approval gate without re-authentication (MFA).
- A human override that contradicts a security policy triggers an alert to the Security Engineer agent.
