# Context Scope

Context Scope defines the strict boundary within which information applies. Context must never escape its authorized scope.

## Scoping Levels

1. **Global**: System-wide rules applicable to every agent and every tenant (e.g., internal ForgeOS system prompts).
2. **Tenant**: Rules applicable to an entire organization's isolated environment. Information from Tenant A is invisible to Tenant B.
3. **Organization**: High-level policies within a tenant (e.g., company-wide coding standards).
4. **Project**: Bounded environments containing specific repositories, requirements, and architectures. The most common working scope.
5. **Workspace**: A specific, temporary developer environment (often tied to a branch or specific infrastructure footprint).
6. **Repository**: Scope restricted to a single Git repository.
7. **Module**: Scope restricted to a specific directory or package within a repository.
8. **Task**: Scope restricted to a single ticket or objective.
9. **Workflow**: Scope restricted to a pipeline of tasks (e.g., "The v1.2 Deployment Workflow").
10. **Agent**: Scope restricted to a specific AI persona (e.g., a Backend Engineer's private scratchpad).
11. **User**: Scope restricted to a human operator's preferences or private data.
12. **Artifact**: Scope restricted to a single, authoritative document.
13. **Environment**: Scope restricted to deployment targets (e.g., Staging vs. Production context).

## Enforcement
The Context Engine's `FILTERING` stage explicitly requires a scope match. If an agent is executing a task in `Project_X`, any candidate information tagged strictly with `Project_Y` is immediately discarded, regardless of semantic relevance.
