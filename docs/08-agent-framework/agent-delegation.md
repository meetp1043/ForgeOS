# Agent Delegation

Delegation is the process by which an agent assigns a sub-task to another agent. This is fundamental to hierarchical team structures in ForgeOS.

## The Rule of Delegation Authority
**An agent may only delegate work if it possesses the required organizational authority.**
- A Project Manager can delegate to a Backend Engineer.
- An Architect can delegate to a Database Engineer.
- An Engineering Manager can delegate to a Frontend Engineer.
- **A Backend Engineer CANNOT delegate an architecture decision back to itself to bypass constraints.**

## Delegation Process

1. **Delegation Request**: The delegating agent identifies a sub-task and issues a structured `DELEGATION` message via the Workflow Engine.
2. **Validation**: The Workflow Engine verifies the delegating agent has the authority to assign this work.
3. **Assignment**: The sub-task is queued and the Agent Framework creates a new Agent Instance for the subordinate role.
4. **Tracking**: The delegating agent receives an async task handle and continues its own work or enters a `BLOCKED` state waiting for the subordinate.
5. **Result**: The subordinate agent finishes, and its structured `Result` is routed back as context to the delegating agent.

## No Infinite Loops
The Framework enforces a maximum delegation depth to prevent circular dependencies (e.g., Agent A delegates to Agent B, which delegates to Agent A).
