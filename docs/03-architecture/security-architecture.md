# Security Architecture

ForgeOS executes AI-generated code. Security is the most critical non-functional requirement.

## Authentication & Authorization
- **Authentication**: Delegated to OAuth2/OIDC providers (GitHub, Google, Microsoft Entra).
- **Authorization**: Role-Based Access Control (RBAC) enforced at the API Controller layer using Spring Security (`@PreAuthorize("hasRole('PROJECT_ADMIN')")`).

## Agent Security (Least Privilege)
- **Tool Permissions**: Agents are only granted tools explicitly required for their role. (e.g., The Architect agent cannot execute `git push`).
- **Prompt Injection Defenses**: 
  - Strict system prompt fencing.
  - LLM outputs are treated as untrusted and must be validated before parsing.
- **Untrusted Repository Defenses**: If ForgeOS is analyzing an external, untrusted codebase, the context engine parses files in a sandboxed, read-only mode to prevent malicious comments from hijacking the agent prompt.

## Execution Sandboxing
- All `run_command` operations execute in an ephemeral, unprivileged Docker container with dropped capabilities and no host volume mounts outside the specific project workspace.
- **Network Egress**: The sandbox is blocked from accessing the internal ForgeOS database, cache, or message broker.

## Data Security
- **Encryption in Transit**: TLS 1.2+ mandatory for all connections.
- **Encryption at Rest**: AWS KMS or equivalent for database volumes.
- **Secrets Management**: ForgeOS never stores plain-text API keys (e.g., AWS keys, OpenAI keys). They are encrypted in the database and only decrypted in memory just-in-time for execution, injected securely into the sandbox environment variables, and scrubbed from all observability logs.
