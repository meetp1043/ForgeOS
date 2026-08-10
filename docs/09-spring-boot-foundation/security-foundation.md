# Security Foundation

Spring Security is included from Phase 09 to establish the integration boundary.

## Current State
- The `SecurityConfiguration` class disables CSRF (as this will be a stateless API) and requires authentication for all endpoints except Actuator health checks.
- Full OAuth2/JWT implementation is deferred to a future phase.

## Future Extensibility
- Phase 11 will integrate JWT parsing and Role-Based Access Control (RBAC).
- The `identity` module will handle the mapping of JWT claims to internal ForgeOS Tenants and Roles.
