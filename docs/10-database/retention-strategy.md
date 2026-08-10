# Retention Strategy

Currently, ForgeOS uses **Hard Deletes** for all foundation records.

## Future Soft Deletes
Soft deletes (`@SQLDelete` and `@Where` clauses) may be introduced later for specific entities like `users` or `organizations` for compliance reasons. 

However, soft deletes significantly complicate unique constraints and foreign key relationships. Therefore, we do not implement them prematurely.

## Audit Logs
Audit logs (`audit_events`) are strictly **append-only** and immutable. They are never deleted as part of standard application workflows. A separate background archival process should manage audit table sizes over time.
