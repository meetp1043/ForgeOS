# Approval Architecture

The Approval Engine acts as the human-in-the-loop brake system for the ForgeOS orchestration state machine.

## Risk Levels and Gates

- **LOW Risk**: (e.g., writing documentation, formatting code). No human approval required. Execution proceeds autonomously.
- **MEDIUM Risk**: (e.g., modifying source code). Approval is requested, but organization policies can allow AI Code Reviewers to auto-approve passing builds.
- **HIGH Risk**: (e.g., database schema changes, staging deployments). The state machine is explicitly paused. A Human Administrator must click "Approve".
- **CRITICAL Risk**: (e.g., production deployment, dropping tables, rotating secrets). Requires explicit re-authentication, MFA, or multi-person approval.

## Approval Lifecycle
- **Approval Request**: An event fired by the Orchestrator pausing an Agent's workflow.
- **Delegation**: A user can forward an approval request to a subject matter expert.
- **Approval**: User signs off; the workflow resumes.
- **Rejection**: User denies the action. The workflow transitions to a compensation or fallback state.
- **Expiration**: If an approval times out (e.g., 48 hours), the pending workflow is safely canceled.
- **Emergency Stop**: A global override that rejects all pending approvals and halts all active agents for a project.
