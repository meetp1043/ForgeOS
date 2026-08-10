# Permission Filtering

While Security Filtering protects secrets and tenant boundaries, Permission Filtering enforces **Least Privilege** based on the agent's assigned role.

## The Concept of Agent Privilege

In ForgeOS, agents possess distinct roles (e.g., `FRONTEND_ENGINEER`, `DATABASE_ADMINISTRATOR`, `COST_OPTIMIZER`). A piece of context might be perfectly secure and belong to the correct project, but still be inappropriate for a specific agent.

### Example: Frontend Agent
A Frontend Agent is assigned to fix a React button.
- **Allowed Context**: Frontend source code, API contracts (OpenAPI specs), UI requirements, Figma design links, relevant frontend tests.
- **Filtered Context**: Production database credentials, unrelated HR data, backend Kubernetes deployment scripts.

### Why filter?
1. **Focus**: Feeding backend deployment scripts to a frontend agent pollutes its context and degrades its coding performance.
2. **Blast Radius**: If the Frontend Agent is compromised via prompt injection, it cannot destroy the database if it never had the database coordinates in its context to begin with.

## Context != Authorization
**Context must not grant permissions.** 

Supplying a database schema in the context package does not give the agent the network permission to execute `DROP TABLE`. The Context Engine only supplies *knowledge*; the Tool System and Security System enforce actual execution *authority*. Context retrieval must never become a hidden mechanism for privilege escalation.
