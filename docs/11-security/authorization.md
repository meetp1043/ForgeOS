# Authorization & RBAC

Authorization ensures an *authenticated* user is allowed to perform an action on a specific resource.

## Two-Phase Evaluation
1. **Tenant Validation Filter**: Extracts `X-Tenant-ID` and verifies the user is a member of that Organization. If valid, populates `TenantContextHolder`.
2. **Method Security**: Controllers use `@PreAuthorize("hasPermission(#tenantId, 'PROJECT_CREATE')")`. The `SecurityPermissionEvaluator` extracts the user's role in the specific tenant and checks the required permission.

## Roles
- `OWNER`
- `ADMIN`
- `MEMBER`
- `VIEWER`

## Explicit Deny
If a user tries to access a project belonging to a tenant they are not part of, the `TenantValidationFilter` will reject the request with `403 Forbidden` before the controller logic is ever reached.
