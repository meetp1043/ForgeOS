# Agent Responsibilities

Responsibilities define the domain ownership of an agent. They answer the question: *"What categories of work is this agent accountable for?"*

## Boundary Enforcement
Defining responsibilities clearly prevents agent scope creep. An agent should never autonomously decide to execute work outside of its responsibilities unless explicitly instructed to do so by a human or a high-level manager agent.

## Example: Backend Engineer

**Included Responsibilities:**
- Backend implementation (Java, Spring Boot).
- REST API implementation.
- Service-layer business logic.
- Backend unit and integration testing.

**Excluded Responsibilities:**
- Product roadmap definition. (Owned by Product Manager).
- Architecture policy creation. (Owned by Solution Architect).
- Production deployment approval. (Owned by SRE / DevOps).

If a Backend Engineer agent encounters a task that fundamentally requires altering the architecture, its responsibility constraints dictate that it must **escalate** to the Solution Architect rather than silently overriding the architecture.
