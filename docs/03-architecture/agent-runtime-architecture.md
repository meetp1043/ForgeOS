# Agent Runtime Architecture

The Agent Runtime is responsible for executing individual agents.

## Definitions
- **Agent Definition**: A static configuration (YAML/DB) defining a role (e.g., "Backend Developer"), its system prompt, and allowed tools.
- **Agent Instance**: An active, running instantiation of an Agent Definition assigned to a specific Task.
- **Capabilities/Permissions**: The strict subset of tools the instance is allowed to use (e.g., QA agent cannot use the `deploy` tool).
- **Agent Context**: The ephemeral prompt window, containing the system prompt, task details, retrieved memory, and current conversation history.

## Execution Lifecycle
1. **Created**: The Orchestrator instantiates the agent.
2. **Assigned**: A specific Task is bound to the agent.
3. **Context Loaded**: The Context Engine populates the Agent Context via RAG and static rules.
4. **Planning**: The agent emits a thought block detailing its intended approach.
5. **Tool Execution**: A loop where the agent calls tools (e.g., `read_file`, `run_command`), the Sandbox executes them, and the result is appended to the context.
6. **Verification**: The agent verifies its own work against the acceptance criteria.
7. **Completed / Failed / Escalated**: The agent loop terminates. It either succeeds, exceeds its retry/token limit (Failed), or detects it needs human clarification (Escalated).
