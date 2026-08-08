# Multi-Tenancy Architecture

ForgeOS is designed to be deployed as a SaaS platform where multiple organizations (Tenants) share the same underlying infrastructure securely.

## Tenant Isolation Levels

- **Data Isolation**: 
  - PostgreSQL uses a discriminator column (`tenant_id`) on all root entities. Queries must explicitly filter by `tenant_id` (enforced via Hibernate filters or Postgres RLS).
  - MongoDB documents include a `tenantId`.
  - Redis keys are prefixed with `tenant:{id}:`.
- **Workspace Isolation**:
  - Each tenant's project workspaces are mapped to strictly isolated directories or dedicated Docker volumes. A container spawned for Tenant A cannot mount or see Tenant B's volume.
- **Execution Isolation**:
  - The Execution Sandbox restricts network and CPU resources per tenant to prevent "noisy neighbor" scenarios where one tenant's agent consumes all compute.
- **Credential Isolation**:
  - BYOK (Bring Your Own Key) secrets are encrypted using a tenant-specific salt/key wrapping strategy.
