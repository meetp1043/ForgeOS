# Architecture Integration

Architecture context defines *how* the software must be built to ensure consistency, scalability, and security across the ForgeOS environment.

## Key Architectural Elements
Context should include:
- **Approved Architecture**: High-level topology (e.g., Modular Monolith vs. Microservices).
- **ADRs (Architecture Decision Records)**: Formal decisions on specific technologies or patterns.
- **Module Boundaries**: Clear delineations of what code belongs where.
- **Technology Decisions**: Language versions, frameworks, and libraries.
- **Integration Contracts**: OpenAPI specs, GraphQL schemas, message queue event schemas.
- **Constraints**: System limitations.
- **Security Decisions**: Authentication and authorization patterns.

## The Rule of Stale Architecture
**Never treat an old, superseded ADR as current architecture.**

If the Context Engine retrieves ADR-001 ("Use MongoDB") and ADR-005 ("Migrate from MongoDB to PostgreSQL"), the Context Package must present ADR-005 as the authoritative truth. If ADR-001 is included, it must be flagged as historical context only. An agent must never infer architecture that contradicts an active ADR.
