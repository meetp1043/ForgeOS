# Database Testing Strategy

Testing database integration is critical to ensuring schema validity.

## Testcontainers
We use **Testcontainers** (specifically `testcontainers-postgresql`) to spin up real, ephemeral Postgres databases during the Maven `test` phase.

## Validation Goals
1. **Migration Integrity**: Ensures that `V1__init_forgeos_schema.sql` actually compiles and runs on a real Postgres engine, validating syntax and constraint logic.
2. **Repository Integrity**: Ensures that our `@Entity` mappings correctly match the SQL schema created by Flyway, preventing runtime crashes.
