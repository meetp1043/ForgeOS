# Threat Model

| Threat | Attack Scenario | Mitigation |
|--------|-----------------|------------|
| Cross-Tenant Access | User provides ID of an Organization they don't belong to. | `TenantValidationFilter` intercepts, verifies `organization_memberships`, and blocks 403. |
| Privilege Escalation | `MEMBER` attempts to create an Agent (requires `AGENT_CREATE`). | `@PreAuthorize` uses `SecurityPermissionEvaluator` which maps the user's tenant role to Permissions. `MEMBER` lacks `AGENT_CREATE`. Blocks 403. |
| JWT Forgery | Attacker modifies JWT claims to become Admin. | JWT signature validation via `JwtService` using strict `HMAC-SHA256` ensures tampered tokens are rejected. |
| Token Replay | Attacker steals old Access Token. | Short lifespan (15 mins). Refresh token can be revoked in DB. |
| SQL Injection | Attacker sends malicious SQL in email. | Spring Data JPA handles all parametrization. |
| CSRF | Attacker forces user browser to perform state-changing action. | CSRF disabled because we do not use Cookies for authentication; Bearer tokens must be explicitly read by client code, neutralizing CSRF. |
