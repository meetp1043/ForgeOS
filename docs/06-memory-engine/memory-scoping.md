# Memory Scoping

Scoping is the foundational boundary that prevents memory cross-contamination. 
A memory belonging to Project A must absolutely not appear in the context of an agent working on Project B, even if the semantic similarity is exceptionally high.

## Scope Boundaries

Every memory entry is strictly bound to one or more of the following scopes:

1. **User Scope**: Memory unique to a single human user (e.g., UI preferences).
2. **Organization (Tenant) Scope**: Memory applicable across an entire company or tenant (e.g., standard security baselines).
3. **Project Scope**: Memory specific to a given codebase or application (e.g., architecture decisions, feature requirements).
4. **Workspace Scope**: Memory specific to a temporary engineering sandbox or branch.
5. **Task Scope**: Memory tied to a specific unit of work (e.g., Jira ticket #1234).
6. **Workflow Scope**: Memory tied to a specific orchestrator execution run.
7. **Agent Scope**: Memory tied to an individual AI agent instance's experience.
8. **Artifact Scope**: Memory inextricably linked to a specific document or file.

## Isolation Rules

- **Default Deny**: The retrieval engine operates on a default-deny policy. An agent must supply its active Project ID, Tenant ID, and Task ID when querying memory.
- **Upward Visibility**: An agent working in a Task Scope can see Project Scope and Organization Scope memories, but cannot see other lateral Task Scopes unless explicitly linked.
- **Tenant Isolation**: See [Multi-Tenancy](memory-multi-tenancy.md). Tenant scoping is an absolute physical or logical firewall.
