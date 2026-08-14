# Multi-Tenant SaaS Platform & Tenant Lifecycle (Phase 26)

## Overview
ForgeOS is designed as a Multi-Tenant SaaS platform. This phase implements the foundational boundaries guaranteeing strict isolation of data (workflows, tasks, billing, usage) between discrete tenants (customers).

## Core Principles
1. **Never Trust Network Boundaries**: A tenant ID injected by an edge proxy or UI is not inherently trusted. It must be verified against the authenticated user's memberships via `TenantValidationFilter`.
2. **Fail Closed**: If a user attempts to access a Tenant they don't belong to, or if the Tenant is Suspended, access is denied (`403 FORBIDDEN`).
3. **Idempotency**: Usage metering and tenant provisioning handle bursts safely using idempotency keys.

## Services
- **`TenantProvisioningService`**: Handles the onboarding flow. Transitions a new tenant from `PROVISIONING` -> `ACTIVE` while setting up default quotas, initial features (`AI_MODEL_ACCESS`), and the initial Administrator membership.
- **`UsageMeteringService`**: Listens to domain events (e.g., token usage) and idempotently records it in `UsageRecordEntity`. It dynamically evaluates `QuotaDefinitionEntity` to detect and publish `tenant.quota.exceeded` limits.
- **`EntitlementService`**: A generalized authorization boundary answering: "Is Tenant X allowed to use Feature Y based on their active subscription plan?".

## Security Filter Hierarchy
1. `ApiKeyAuthenticationFilter` / `JwtAuthenticationFilter` (Identifies the user)
2. `TenantValidationFilter` (Validates the request's `X-Tenant-ID` matches the user's actual database `OrganizationMembershipEntity` for that Tenant).

This prevents User A (Tenant A) from spoofing headers to access Tenant B.
