# Tenant Isolation Strategy

## Overview
A critical requirement is that Tenant A cannot access Tenant B's data, even if Tenant A knows a valid UUID for a Project belonging to Tenant B.

## Database Filtering vs Authorization
While `TenantValidationFilter` prevents a user from arbitrarily specifying `X-Tenant-ID: <Tenant B's ID>`, we still need to ensure that database queries are safe.

If a user in Tenant A calls `GET /api/projects/<Tenant B's Project UUID>`:
1. The `X-Tenant-ID` is correctly set to Tenant A.
2. The Repository must execute: `projectRepository.findByIdAndOrganizationId(projectId, TenantContextHolder.getTenantId())`.
3. This will safely return `Optional.empty()` because the Project does not belong to Tenant A, resulting in a 404 (preventing enumeration) or a 403.
