# Tool Execution

The Tool Execution pipeline manages the exact moment an agent reaches out to alter the system or request data. It is a highly constrained, heavily audited chokepoint.

## The Execution Pipeline

When the LLM outputs a tool call request (e.g., `{"name": "execute_query", "arguments": {"sql": "SELECT * FROM users"}}`):

1. **Tool Request**: The Agent Framework intercepts the LLM output.
2. **Authorization**: The framework checks if the `execute_query` tool is in the agent's authorized `Tools` list.
3. **Input Validation**: The JSON arguments are validated against the tool's JSON Schema. (e.g., SQL injection heuristics might be applied here).
4. **Risk Evaluation**: The framework evaluates the specific arguments against the [Risk Classification](agent-risk-classification.md). (e.g., `SELECT` is Medium risk, `DROP` is Critical risk).
5. **Approval**: If the risk exceeds the agent's autonomy threshold, the agent transitions to `WAITING_FOR_APPROVAL`.
6. **Sandbox**: The tool executes within the required isolation boundary (e.g., a short-lived Docker container for bash commands).
7. **Execution**: The actual underlying system is invoked.
8. **Output Validation**: The result is captured. If it is 50MB of raw logs, it is truncated/compressed to prevent breaking the LLM's context window.
9. **Audit**: The request, arguments, approval status, and output are logged to the Execution Trace.
10. **Result**: The formatted output is appended to the agent's prompt, and LLM inference resumes.
