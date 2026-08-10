# Migration Strategy

We use **Flyway** for all schema modifications.

## Rules
1. **Ordered & Repeatable**: Migrations are strictly ordered (e.g., `V1__init.sql`, `V2__add_index.sql`).
2. **Immutability**: Once a migration is merged to `master` and deployed, it **cannot** be modified. New changes require a new `V{Next}` script.
3. **No Automatic DDL**: `spring.jpa.hibernate.ddl-auto` must be set to `validate` (or `none`) in higher environments. `update` is strictly forbidden as it can lead to accidental data loss or unexpected locking.
4. **Environment Agnostic**: Migration SQL must be standard PostgreSQL and must not contain environment-specific hardcoded data.
