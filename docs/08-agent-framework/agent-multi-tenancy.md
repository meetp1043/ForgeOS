# Agent Multi-Tenancy

ForgeOS is designed to host multiple independent software organizations (Tenants). 

## Strict Isolation

- **Registry Isolation**: Tenant A cannot see or use Tenant B's custom Agent Definitions.
- **Context Isolation**: An Agent Instance is strictly bound to a `Tenant_ID`. The Context Engine uses this ID to filter all vector searches.
- **Memory Isolation**: An agent writing to the Memory Engine automatically tags the fact with the `Tenant_ID`.
- **Sandbox Isolation**: Tool sandboxes run in separate network namespaces per Tenant. An agent from Tenant A cannot ping a test database belonging to Tenant B.
- **Cost Isolation**: Budgets are tracked and billed per Tenant.

## Global vs Local Agents
- **Global Agents**: Standard roles defined by ForgeOS (e.g., the default `Backend Engineer`). Available to all tenants, but their runtime context and memory are strictly isolated.
- **Local Agents**: Custom roles created by a specific Tenant (e.g., `Tenant A's Legacy Fortran Specialist`). Only visible and executable within that Tenant.
