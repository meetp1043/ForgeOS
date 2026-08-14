# Identity, Authentication & Authorization Platform (Phase 25)

The Identity Platform represents the ForgeOS Zero-Trust boundary. 
Every API, workflow, and model invocation is guarded by this centralized subsystem.

## Architectural Principles
1. **Authentication vs Authorization**: Who you are (Authentication) does not dictate what you can do (Authorization). Just because an Agent has an active API key does not mean it has access to read another user's project.
2. **Zero-Trust**: Services (like Model Gateway and Agent Runtime) do not trust network boundaries. They rely on valid tokens and Authorization Policy Engine checks.
3. **Least Privilege (Fail Closed)**: The `AuthorizationPolicyEngine` defaults to `DENY`.

## Features
- **Token Rotation & Reuse Detection**: Refresh tokens are assigned a `familyId`. When a user refreshes a token, the old one is revoked, and a new one is issued with the same family ID. If an attacker steals and uses the *old* revoked token, ForgeOS instantly detects the reuse, revokes all tokens in the entire family, and fires an `auth.token.reuse_detected` event.
- **Brute Force Lockouts**: User accounts automatically lock out for 15 minutes after 5 consecutive failed login attempts, preventing simple dictionary attacks.
- **API Keys**: Non-human agents and headless services authenticate using heavily scoped `API Keys`. The database stores only a BCrypt hashed secret. If compromised, keys can be instantly revoked.
- **Policy Engine**: RBAC/ABAC engine evaluating decisions based on Tenant constraints and Project scoping. `Developer` roles can write to repositories, whereas `Viewer` roles can only read.
- **Audit Trails**: Critical security events (login success, login failure, account locked, token reuse detected) are published for SIEM aggregation via the Phase 23 Event Bus (Spring Application Events).
