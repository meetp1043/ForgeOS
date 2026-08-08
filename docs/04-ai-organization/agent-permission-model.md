# Agent Permission Model

ForgeOS uses a strict Least Privilege permission model for agents. A permission dictates whether a tool invocation will be authorized by the Execution Sandbox.

## Common Permissions
- `FILE_READ`: Read files in the project workspace.
- `FILE_WRITE`: Create or modify files in the workspace.
- `FILE_DELETE`: Delete files.
- `GIT_READ`: Execute `git status`, `git log`.
- `GIT_BRANCH`: Execute `git checkout -b`.
- `GIT_COMMIT`: Execute `git commit`.
- `GIT_PUSH`: Execute `git push` to origin.
- `GIT_PR`: Open Pull Requests via API.
- `DATABASE_READ`: Execute `SELECT` queries.
- `DATABASE_WRITE`: Execute `INSERT`/`UPDATE`/`DELETE`.
- `DATABASE_MIGRATION`: Execute DDL (e.g., `CREATE TABLE`, `DROP COLUMN`).
- `CLOUD_READ`: Read cloud infrastructure state.
- `CLOUD_WRITE`: Provision or destroy cloud resources.
- `DEPLOY_STAGING`: Trigger deployment to non-production environments.
- `DEPLOY_PRODUCTION`: Trigger deployment to production.
- `SECRET_READ`: Access decrypted secrets (Rarely granted to standard agents).

## Mapping to Roles
- **Frontend Engineer**: Has `FILE_READ`, `FILE_WRITE`, `GIT_BRANCH`, `GIT_COMMIT`.
- **QA Engineer**: Has `FILE_READ`, `GIT_READ` (Cannot write code).
- **Code Review Engineer**: Has `FILE_READ`, `GIT_READ`, `GIT_PR`.
- **Database Engineer**: Has `FILE_READ`, `FILE_WRITE`, `DATABASE_READ`, `DATABASE_WRITE`, `DATABASE_MIGRATION`.
