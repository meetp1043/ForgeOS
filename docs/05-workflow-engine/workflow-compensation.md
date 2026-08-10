# Workflow Compensation

Compensation defines the "undo" or "cleanup" actions taken when a workflow fails after some steps have already succeeded.

## Key Distinction

| Strategy | Description | Example |
| :--- | :--- | :--- |
| **Rollback** | Reversing an operation to its exact prior state. | `git revert` a commit. |
| **Compensation** | Performing a new, forward-moving action that logically neutralizes the effect. | If a staging deployment succeeded but production failed, the staging environment is torn down. |
| **Manual Recovery** | The operation cannot be automatically reversed. Human intervention is required. | A third-party API call that already charged a payment cannot be automatically refunded. |

## Compensation Rules
1. **Not all operations are reversible.** The workflow definition must explicitly declare which steps have compensating actions and which require manual recovery.
2. **Compensation executes in reverse order.** If steps A → B → C ran successfully and step D fails, compensation runs: C_compensate → B_compensate → A_compensate.
3. **Compensation must be idempotent.** If compensation itself is retried (e.g., after a crash during compensation), it must produce the same result.
4. **Compensation failures escalate immediately.** A failed compensation is a critical event and is escalated to human intervention without further retry.

## Examples
- **Git Commit**: Compensated by `git revert`.
- **Database Migration**: Compensated by a reverse migration script (if one was generated).
- **Cloud Resource Provisioning**: Compensated by `terraform destroy` for the specific resource.
- **Production Deployment**: Compensated by redeploying the previous known-good version.
- **External API Call**: Marked as `MANUAL_RECOVERY` — logged and escalated.
