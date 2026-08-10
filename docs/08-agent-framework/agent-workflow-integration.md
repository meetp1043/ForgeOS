# Workflow Integration

The Agent Framework and the Workflow Engine are tightly coupled but conceptually distinct.

## Division of Responsibilities
- **Workflow Engine**: Owns the long-term state machine, the Jira/Linear tickets, dependency graphs, and multi-agent orchestration.
- **Agent Framework**: Owns the short-term, atomic execution of a single task by a single agent.

## Integration Flow

1. **Assignment**: The Workflow Engine identifies a `READY` task and calls the Agent Framework's Selection API.
2. **Execution**: The Agent Framework takes over, instantiates the agent, and runs the LLM loop.
3. **Return**: The Agent Framework returns a validated `Result` (Success, Failure, or Escalation).
4. **Transition**: The Workflow Engine parses the result and transitions the ticket to the next phase (e.g., moving a ticket from `In Progress` to `In Review`).

## Boundary Enforcement
**Agents must not directly modify workflow state outside authorized interfaces.**
An agent cannot simply run an SQL query against the Workflow Engine's internal database to mark its own ticket as "Done". It must emit a `Result` payload, which the Workflow Engine processes.
