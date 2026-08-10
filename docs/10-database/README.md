# ForgeOS Database Foundation (Phase 10)

This directory documents the persistence layer architecture for ForgeOS.

The backend uses **PostgreSQL** as the primary relational datastore, **Spring Data JPA** for ORM mapping, and **Flyway** for automated, versioned database migrations.

## Index

1. [Architecture & Boundaries](database-architecture.md)
2. [Database Boundaries](database-boundaries.md)
3. [Tenant Isolation](tenant-isolation.md)
4. [Schema Strategy](schema-strategy.md)
5. [Migration Strategy](migration-strategy.md)
6. [Indexing Strategy](indexing-strategy.md)
7. [Transaction Strategy](transaction-strategy.md)
8. [Locking Strategy](locking-strategy.md)
9. [Identifier Strategy](identifier-strategy.md)
10. [Retention Strategy](retention-strategy.md)
11. [Audit Storage](audit-storage.md)
12. [Artifact Storage](artifact-storage.md)
13. [Redis Boundary](redis-boundary.md)
14. [Future Vector Storage](future-vector-storage.md)
15. [Testing Strategy](database-testing.md)
