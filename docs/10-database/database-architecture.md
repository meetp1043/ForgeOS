# Database Architecture

ForgeOS utilizes a **Modular Monolith** architecture with a single **PostgreSQL** database.

## Principles
1. **Single Source of Truth**: PostgreSQL handles all critical relational state (Users, Organizations, Projects, Agent Definitions, Workflows).
2. **Explicit Migrations**: `ddl-auto: create` is strictly prohibited in production. We use **Flyway** (`V1__init_forgeos_schema.sql`).
3. **UUID Keys**: All primary keys are `UUID` types. This prevents enumerable IDs (e.g. `user/1`, `user/2`) and simplifies distributed ID generation across modular components.
4. **JSONB Flexibility**: Columns like `agent_definitions.capabilities` or `audit_events.payload` use PostgreSQL `JSONB` to accommodate dynamic structured data without requiring a rigid EAV schema or immediate NoSQL adoption.
