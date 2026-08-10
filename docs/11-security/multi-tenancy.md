# Multi-Tenancy Strategy

ForgeOS uses a **Logical Isolation** approach within a single PostgreSQL database. 

## Tenant Validation
The `TenantValidationFilter` ensures that every request providing an `X-Tenant-ID` is strictly verified against the `organization_memberships` table.

## Tenant Context Holder
To prevent passing `tenantId` manually down through every service layer, we utilize a `ThreadLocal` called `TenantContextHolder`.
- Repositories should ideally reference `TenantContextHolder.getTenantId()` for all multi-tenant queries (or receive it strictly from business logic that has already validated it).
- Background asynchronous tasks (Spring `@Async` or Messaging) must safely copy this context if acting on behalf of a tenant.
