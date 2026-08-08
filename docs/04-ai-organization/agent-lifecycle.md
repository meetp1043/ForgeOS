# Agent Lifecycle

An agent in ForgeOS is an ephemeral worker instantiated to complete a specific task.

## Lifecycle Phases
1. **Definition**: The agent profile (prompt, tools) is defined in code or DB.
2. **Created**: The Orchestrator creates an active instance of the agent profile.
3. **Assigned**: A Task is bound to the agent instance.
4. **Initializing**: The Context Engine gathers necessary memory, codebase snippets, and tools.
5. **Executing**: The core tool-loop where the agent interacts with the LLM API and the Sandbox.
6. **Waiting**: The agent is paused, awaiting an asynchronous event (e.g., a CI build to finish).
7. **Blocked / Awaiting Approval**: The agent hit an approval gate and is paused.
8. **Completed**: The agent finished its task successfully.
9. **Failed**: The agent exceeded retries, crashed, or gave up.
10. **Terminated**: The instance is destroyed.
