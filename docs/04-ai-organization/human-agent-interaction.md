# Human-Agent Interaction

Humans act as the ultimate authorities and stakeholders in the ForgeOS organization.

## Human Capabilities

- **Observe**: Humans can view real-time streaming logs of any active agent execution, including the agent's internal thought process.
- **Instruct**: Humans provide the initial goals and can inject new context mid-flight via the CEO or Product Manager agents.
- **Approve / Reject**: Humans act as the gatekeepers for High and Critical risk actions (e.g., Production Deployments).
- **Pause / Resume**: A human can pause a runaway project, preventing further token spend, and resume it later.
- **Stop**: A human can permanently terminate a workflow.
- **Override**: A human can manually edit an artifact (e.g., rewriting the PRD or fixing a bug manually). Agents must accept the human override as absolute truth.

## Audit Trail
Every human interaction, especially overrides and approvals, is permanently logged in the project history for compliance and auditing.
