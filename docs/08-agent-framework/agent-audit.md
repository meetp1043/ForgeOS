# Agent Audit

The Audit Log is an immutable, cryptographic record of system state changes. While Observability tracks *health*, Audit tracks *accountability*.

## Audited Events

The Framework mandates permanent audit records for:
- **Agent Creation & Registration**: When a new agent definition is added to the system.
- **Agent Version Changes**: When an agent's prompt, permissions, or tools are modified.
- **Permission Changes**: When an agent's authorized capabilities are escalated or reduced.
- **Model Policy Changes**: Forcing an agent to use a different provider.
- **Execution Lifecycle**: The start, completion, failure, or cancellation of an Execution ID.
- **Tool Calls**: Every tool invocation and its exact parameters (with secrets masked).
- **Delegation**: When Agent A spawns Agent B.
- **Escalation**: When an agent kicks a problem up the hierarchy.
- **Approval**: The exact timestamp and identity of the Human/Manager who authorized a `HIGH` or `CRITICAL` action.
- **Human Intervention**: Any manual override or pause/resume command.
- **Retirement**: When an agent is permanently disabled.

## Audit Immutability
Audit logs cannot be modified or deleted by any AI agent, regardless of role. They are append-only.
