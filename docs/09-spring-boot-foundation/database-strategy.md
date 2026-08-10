# Database Strategy

**PostgreSQL** is the primary relational datastore.

## Development vs Production
- **Development**: Spring Data JPA is configured with `ddl-auto: update` for rapid prototyping during the foundation phase.
- **Production (Future)**: We will implement **Flyway** or **Liquibase** for strict schema versioning before any production deployment.

## Access Patterns
- Each module manages its own schema logically.
- Modules must not execute SQL joins across module boundaries. If `Workflow` needs `Agent` data, it must request it via a Java API call to the `agent` module.
