# Agent Execution

An Execution is a single, atomic attempt by an [Agent Instance](agent-instance.md) to fulfill a task.

## Execution Core Loop

Once the Instance is initialized, the execution enters an autonomous loop:

1. **Context Ingestion**: The agent parses the System Instructions and Context Package.
2. **Planning**: The agent emits a structured thought process or plan.
3. **Action Proposal**: The agent requests to invoke a Tool (e.g., `search_code("UserController")`).
4. **Tool Execution**: The Framework pauses the LLM, validates the tool request against Permissions, executes the tool in a sandbox, and captures the output.
5. **State Update**: The tool output is appended to the agent's context.
6. **Re-evaluation**: The loop returns to Step 2. The agent decides if it has enough information to complete the task, or if it must invoke another tool.
7. **Result Generation**: The agent decides the task is complete and formats a structured JSON `Result`.

## Failure Paths
- If the token budget is exhausted during the loop, the Execution fails with `BUDGET_EXCEEDED`.
- If a tool continuously errors (e.g., 5 syntax errors in a row), the Framework may forcibly break the loop and fail the Execution with `TOOL_ERROR`.
