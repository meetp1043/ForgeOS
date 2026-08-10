# Workflow Security

Workflow execution must enforce all security boundaries established in `/docs/03-architecture/security-architecture.md` and `/docs/04-ai-organization/agent-security-rules.md`.

## Security Rules

1. **Tenant Isolation**: A workflow instance for Tenant A cannot access state, context, or resources belonging to Tenant B.
2. **Project Isolation**: A workflow step operating in Project X cannot read or modify files in Project Y.
3. **Agent Permissions**: The workflow engine must verify that an assigned agent has the required RBAC permissions before dispatching a step. A workflow cannot grant an agent permissions it does not already possess.
4. **Tool Permissions**: Each tool invocation is authorized against the agent's permission set. Unauthorized tool calls are rejected and logged.
5. **Environment Permissions**: Only agents with `DEPLOY_STAGING` or `DEPLOY_PRODUCTION` permissions can execute deployment steps.
6. **Approval Policy Enforcement**: The engine enforces approval gates as defined. No workflow path can bypass a `CRITICAL` approval gate, even via programmatic override.
7. **Secret Boundaries**: Secrets (API keys, credentials) are never stored in workflow state. They are injected into the Sandbox environment at execution time and scrubbed from all logs and event payloads.
8. **Input Validation**: Workflow inputs are validated against their schema at `VALIDATING` time. Malformed or oversized inputs are rejected.
