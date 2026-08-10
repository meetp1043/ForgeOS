# Security Filtering

Security filtering is the absolute highest priority operation in the Context Engine pipeline. It guarantees that malicious payloads, secrets, and unauthorized data never enter an agent's reasoning loop.

## The Pre-Rank Mandate
**Security filtering must happen before information enters the context package.** It must execute before ranking, before summarization, and before token calculation.

## What is Blocked?

The Security Filter aggressively drops candidates containing:
- **Secrets & Credentials**: Passwords, API keys, OAuth tokens, AWS access keys.
- **Private Keys**: TLS/SSL certificates, SSH private keys.
- **Restricted Artifacts**: Documents flagged as `CONFIDENTIAL` or `RESTRICTED` (unless the agent is explicitly authorized).
- **Other Tenant Information**: Absolute isolation block.
- **Restricted Project Information**: E.g., stopping an agent working on an open-source module from reading proprietary billing logic.

## Defense in Depth
The Context Engine does not rely solely on its own rules. It interfaces with ForgeOS's central **Security System**. The Security System provides the authoritative boolean (`Allow`/`Deny`) for any given candidate.

If the Security System is unreachable, the Context Engine must **Fail Closed**. It cannot assume "Allow".
