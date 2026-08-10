# Workflow Agent Assignment

Agent assignment is the process by which the Orchestrator selects the optimal agent to execute a given workflow step.

## Assignment Criteria

| Criterion | Description |
| :--- | :--- |
| **Role Match** | The step requires a specific role (e.g., "Backend Engineer"). The registry is queried for agents matching that role. |
| **Capability Match** | The step may require specific capabilities (e.g., `JAVA_CODING`, `TERRAFORM`). The agent must have the matching capability tags. |
| **Permission Match** | The agent must possess the RBAC permissions required by the step's tools (e.g., `FILE_WRITE`, `DATABASE_MIGRATION`). |
| **Availability** | If concurrent agent instance limits exist, the engine checks if a new instance can be spawned. |
| **Project Context** | The agent should be scoped to the specific project and tenant. |
| **Technology Fit** | If the project uses Next.js, the Frontend Engineer should be assigned with a Next.js-capable model and prompt. |
| **Cost Policy** | The Model Router may downgrade to a cheaper model if the budget is constrained. |

## Assignment Process
1. The step declares a required `agent_role`.
2. The Orchestrator queries the Agent Registry for all `ACTIVE` agents matching the role.
3. The engine filters by capability, permission, and availability.
4. If multiple candidates remain, the engine selects based on cost efficiency or round-robin.
5. A new Agent Instance is created, bound to the task, and dispatched to the Agent Runtime.

## No Fixed Instances
Agent assignment is dynamic. There is no assumption that a single, persistent "Backend Engineer" instance handles all backend tasks. Each task may spawn a fresh, isolated agent instance.
