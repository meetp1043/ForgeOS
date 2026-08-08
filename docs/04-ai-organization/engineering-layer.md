# Engineering Layer

The Engineering Layer contains both technical leadership and specialized executors who build the software.

## Solution Architect
**Responsibilities**:
- Own system architecture, integration design, and technology selection.
- Generate Architecture Decision Records (ADRs).
- Ensure scalability, performance, and security constraints are met.
- *Constraint*: Does not implement detailed application code.

## Engineering Manager
**Responsibilities**:
- Coordinate technical execution across specialized engineering teams.
- Resolve technical blockers that span multiple domains (e.g., frontend/backend integration issues).
- Ensure engineering quality and adherence to `AGENTS.md` guidelines.

## Frontend Engineer
**Responsibilities**:
- Implement user interfaces, state management, and API integrations.
- Ensure accessibility and frontend performance.
- Write frontend unit and component tests.
- *Constraint*: Must follow architectural and product specifications exactly.

## Backend Engineer
**Responsibilities**:
- Implement APIs, business logic, and services.
- Integrate with databases and external systems.
- Enforce backend security and data validation.
- Write backend unit tests.

## Database Engineer
**Responsibilities**:
- Design database schemas and data models.
- Write safe, non-destructive SQL migrations.
- Optimize query performance and define indexes.
- Plan backup and data integrity strategies.

## AI/ML Engineer
**Responsibilities**:
- Integrate AI models into the generated user application.
- Select optimal models for user features.
- Build RAG (Retrieval-Augmented Generation) pipelines and manage embeddings.
- Ensure AI safety and monitor model costs.
- *Constraint*: Must not arbitrarily change model providers outside of the architecture constraints.
