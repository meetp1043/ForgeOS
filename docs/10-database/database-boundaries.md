# Database Boundaries & Module Ownership

Each table belongs to a specific ForgeOS module. No two modules share ownership of a table, enforcing the Spring Modulith bounded contexts at the database level.

## Ownership Map
- **`identity`**: `users`
- **`organization`**: `organizations`, `organization_memberships`
- **`project`**: `projects`, `workspaces`
- **`agent`**: `agent_definitions`, `agent_versions`
- **`workflow`**: `workflows`, `tasks`
- **`execution`**: `agent_executions`
- **`artifact`**: `artifact_metadata`
- **`approval`**: `approval_requests`
- **`audit`**: `audit_events`

## Data Access Rules
If the `workflow` module needs to know the capabilities of an `agent_version`, it **must not** query `agent_versions` directly using a local repository. Instead, it must call an interface provided by the `agent` module.
This strict decoupling ensures that if the `agent` module is ever extracted to a microservice, the `workflow` module requires no database-level refactoring.
