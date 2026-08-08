# Plugin Architecture

To support a future ecosystem, ForgeOS must allow extensibility without modifying the core monolith.

## Plugin Types
1. **Agent Plugins**: Custom personas with specialized system prompts.
2. **Tool Plugins**: Custom bash scripts, Python utilities, or API wrappers that agents can invoke.
3. **Integration Plugins**: Connectors to third-party tools (e.g., Jira, Slack, PagerDuty).

## Security Boundaries
Plugins are fundamentally untrusted.
- Tool Plugins execute inside the Execution Sandbox.
- Integration Plugins that require ForgeOS internal API access are granted scoped OAuth tokens with minimal permissions.
- Agents cannot dynamically download and execute unverified plugins at runtime without human approval.
