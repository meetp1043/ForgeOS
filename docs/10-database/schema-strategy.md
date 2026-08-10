# Schema Strategy

## Enums
We use `VARCHAR(50)` columns for application enums (e.g., `status` fields) instead of PostgreSQL native `ENUM` types. 
- *Why?* Native enums in Postgres require complex `ALTER TYPE` statements to add new values. For an actively developed AI OS, a simple `VARCHAR` mapped to a Java `@Enumerated(EnumType.STRING)` is much more flexible and avoids migration headaches.

## JSONB
We use PostgreSQL `JSONB` for heavily nested or dynamic data:
- `agent_definitions.capabilities`
- `audit_events.payload`
These map directly to `Map<String, Object>` or custom record types in Java using Hibernate 6's `@JdbcTypeCode(SqlTypes.JSON)`.

## Timestamps
Every mutable entity extends `BaseEntity`, providing:
- `created_at` (TIMESTAMP WITH TIME ZONE)
- `updated_at` (TIMESTAMP WITH TIME ZONE)
Populated automatically via Spring Data JPA's `@EnableJpaAuditing`.
