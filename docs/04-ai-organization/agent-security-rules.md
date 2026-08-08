# Agent Security Rules

Security rules govern the boundaries of agent autonomy to protect the host system and the user's data.

## Fundamental Security Directives

1. **Least Privilege**: Agents are granted only the minimum permissions necessary for their specific role taxonomy.
2. **Sandboxing**: No agent may execute shell commands or code directly on the ForgeOS host machine. All execution occurs in ephemeral, isolated Docker containers.
3. **Secret Isolation**: Agents never see raw API keys or passwords in their prompts. Secrets are injected directly into the Execution Sandbox environment variables by the Orchestrator.
4. **Project Isolation**: An agent operating in Project A has absolutely zero network or filesystem access to Project B.
5. **Tenant Isolation**: An agent operating for Tenant X cannot query memories or context from Tenant Y.
6. **Prompt Injection Defense**: If an agent reads an external, untrusted file (e.g., a third-party GitHub repository), the Orchestrator parses the file in a restricted mode to prevent malicious comments from overwriting the agent's system prompt instructions.
7. **Output Validation**: Agent-generated code is treated as untrusted and must pass static analysis and QA tests before being merged.
