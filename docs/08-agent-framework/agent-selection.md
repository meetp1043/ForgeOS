# Agent Selection

Agent Selection is the decisive phase following [Discovery](agent-discovery.md). When multiple candidates are found, the framework must choose exactly one agent to instantiate.

## The Selection Pipeline

1. **Task**: The Workflow Engine submits a task definition.
2. **Required Capabilities**: Extracted from the task (e.g., `CODE_WRITE`, `Java`).
3. **Role Candidates**: The Registry returns a list of viable agents (e.g., `Backend Engineer v1.2`, `Backend Engineer v2.0`, `Fullstack Engineer v1.0`).
4. **Permission Filtering**: Eliminate candidates lacking the system rights to complete the task.
5. **Technology Compatibility**: Eliminate candidates not trained/prompted for the specific tech stack.
6. **Risk Compatibility**: Eliminate candidates not rated for the task's risk level.
7. **Availability**: Ensure the agent is `ACTIVE` for the specific Tenant.
8. **Cost & Performance**: Between `Backend Engineer v1.2` and `v2.0`, the system may prefer the newer version due to better LLM-as-a-judge scores, or an older, specialized version because it is cheaper to run on smaller models.
9. **Model Compatibility**: If the task forces a local model, the system selects an agent whose `Model Policy` supports local inference.
10. **Agent Selection**: The single optimal Agent Definition Version is chosen.

## Selection Conflicts
If no agent matches the criteria, the selection fails. The Workflow Engine must escalate to a human manager. **Do not randomly assign a dangerously under-permissioned or under-qualified agent.**
