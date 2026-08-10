# Agent Security Policy

The Security Policy binds an agent definition to the ForgeOS core security mechanisms. It determines the risk profile and isolation requirements of the agent at runtime.

## Core Security Constraints

- **Least Privilege**: The agent receives only the permissions explicitly granted in its definition. It cannot escalate its own privileges.
- **Role Isolation**: Agents cannot impersonate other agents or human users.
- **Sandbox Requirement**: Tool execution (especially `Terminal` or `Filesystem`) must occur in isolated, ephemeral containers, not directly on the ForgeOS host.
- **Network Boundaries**: Agents cannot arbitrarily reach out to the public internet unless provided an explicitly allow-listed Web Browser tool.

## Interaction with Security Engine
The Agent Framework relies on the central Security System for authorization checks. 
If an agent requests to execute `aws s3 rm`, the Tool Execution layer intercepts the request, maps it to the `INFRASTRUCTURE_DELETE` permission, and checks the agent's Security Policy. If denied, the tool call fails immediately with a security exception.
