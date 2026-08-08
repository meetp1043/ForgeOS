# Trust and Safety Philosophy

This document defines policy principles to ensure the security, privacy, and safety of ForgeOS users and the software they build. *Note: This is a policy document, not an implementation specification.*

### Secrets and Credentials
- Agents must never output, log, or persist raw secrets.
- Secrets must be injected via secure environment variables or secret managers at runtime.
- If an agent detects a hard-coded secret in a prompt or codebase, it must flag it as a violation immediately.

### User Data and Privacy
- ForgeOS must respect user data privacy. Proprietary code and data must not be used to train models unless explicitly opted-in.
- Agents must not leak context from one workspace/organization into another.

### Destructive Actions
- Any action that deletes data (e.g., dropping a database table) or infrastructure must be explicitly confirmed by a human.

### Production Systems
- Agents should not have direct, unfettered access to production environments.
- Deployments to production must flow through standard CI/CD pipelines with human gates.

### Auditability
- Every action taken by an agent must be logged.
- Logs must detail the agent's identity, the prompt that triggered the action, the model used, and the result.

### Agent Permissions
- Agents operate under the principle of least privilege.
- A frontend agent cannot access database connection strings.

### Prompt Injection and Malicious Repositories
- The system must assume user inputs and third-party repository contents are potentially hostile.
- Prompts must be designed to resist injection attacks.
- Agents must not execute arbitrary binaries discovered in untrusted repositories.

### Untrusted Tools and Generated Code
- Agents must only use explicitly whitelisted tools.
- Generated code must be subjected to static analysis and security scanning before merging.

### Dependency and Supply-Chain Risks
- Agents must prioritize well-maintained, popular, and secure libraries.
- Dependency upgrades must be automatically scanned for known CVEs.
