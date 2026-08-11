# Agent Security

Agents operate with **Least Privilege**.

- **Delegation limits**: An Architect agent cannot blindly delegate root access to a Backend Agent. The child agent is initialized only with the subset of permissions defined in its `AgentDefinition`.
- **Tenant Isolation**: `AgentExecution` entities mandate a `tenantId`. Data access queries and tool executions enforce this tenant boundary.
- **Self-Modification**: Agents cannot manipulate their own risk tolerances, tool permissions, or budget sizes via prompt output. These are static fields enforced by the JVM.
