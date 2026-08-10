# ForgeOS Security Architecture (Phase 11)

This directory documents the authentication, authorization, and multi-tenancy architecture for ForgeOS.

## Principles
1. **Deny by default**: All endpoints require authentication unless explicitly public.
2. **Strict Multi-Tenancy**: Tenant context must be evaluated server-side based on the authenticated user's organization memberships.
3. **Stateless Scalability**: Authentication uses stateless JWTs, while sessions (Refresh Tokens) are stateful for revocability.

## Index
- [Authentication](authentication.md)
- [Authorization](authorization.md)
- [Multi-Tenancy](multi-tenancy.md)
- [RBAC & Roles](rbac.md)
- [Permissions Map](permissions.md)
- [Session Management](session-management.md)
- [Token Strategy](token-strategy.md)
- [Password Security](password-security.md)
- [Tenant Isolation Strategy](tenant-isolation.md)
- [Threat Model](threat-model.md)
