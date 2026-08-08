# Agent Versioning

Every component of an agent's definition is versioned to ensure reproducibility and auditability.

## Versioned Components
- **Role Definition**: The conceptual description of responsibilities and authority.
- **System Prompt**: The exact text injected into the LLM context. Prompt changes are the most frequent versioning event.
- **Tool Schema**: The JSON schemas defining available tools and their input/output contracts.
- **Permissions**: The RBAC permission set granted to the agent.
- **Model Policy**: The specific LLM model(s) the agent is allowed to use (e.g., `gpt-4o`, `claude-3.5-sonnet`).
- **Configuration**: Hyperparameters such as temperature, max tokens, and retry limits.

## Versioning Strategy
- Versions follow Semantic Versioning: `MAJOR.MINOR.PATCH`.
  - **MAJOR**: Breaking changes to the role's responsibilities or authority (e.g., the Backend Engineer is now also responsible for database migrations).
  - **MINOR**: New tool additions or prompt refinements that enhance capabilities.
  - **PATCH**: Bug fixes in prompt wording or tool schema corrections.

## Execution Traceability
Every agent execution record in the database must log the exact version of the role, prompt, tool schema, and model that was active at the time of execution. This allows engineers to diagnose regressions by diffing agent versions.
