# Role-Based Access Control (RBAC)

ForgeOS uses a highly explicit RBAC system scoped by Organization.

## Roles
- `OWNER`: Full administrative access to the entire organization and all its projects.
- `ADMIN`: Administrative access to the organization (manage members, projects).
- `MEMBER`: General access to read organization details and participate in projects where explicitly invited (or all projects, depending on future project-level RBAC).
- `VIEWER`: Read-only access to organization and project metadata.

## Enforcement
Roles are not hard-coded in `if` statements. Instead, roles are mapped to `Permission` enums. Security checks evaluate *Permissions*, not Roles.
