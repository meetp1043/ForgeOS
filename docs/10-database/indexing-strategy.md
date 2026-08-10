# Indexing Strategy

Indexes are created intentionally to optimize specific access patterns.

## Default Indexes
Foreign keys do not automatically create indexes in PostgreSQL. Therefore, we explicitly index all `_id` foreign key columns that will be used in joins or filter criteria.

Examples from `V1`:
- `CREATE INDEX idx_project_org ON projects(organization_id);`
- `CREATE INDEX idx_workflow_proj ON workflows(project_id);`
- `CREATE INDEX idx_exec_task ON agent_executions(task_id);`

## Unique Constraints
We use `UNIQUE` constraints (which implicitly create unique indexes) for invariants:
- `users.email`
- `organizations.name`
- `organization_memberships(organization_id, user_id)`
- `agent_versions(agent_definition_id, version_string)`
