# Agent Tool Security

Tools are the primary attack vector for an autonomous agent. 

## Tool Security Requirements

Every tool defined in the Tool System must have:
- **Identity**: A unique name (e.g., `git_commit`).
- **Risk Classification**: (e.g., `MEDIUM`).
- **Permissions Required**: (e.g., `GIT_COMMIT`).
- **Input Validation**: Strict JSON schemas defining exactly what arguments are allowed.
- **Output Validation**: Sanitization of the output before returning it to the LLM (e.g., scrubbing secrets from curl responses).
- **Sandbox Requirement**: Definition of the execution environment (e.g., `network_isolated_container`).
- **Timeout**: Hard limit on execution time.
- **Audit Flag**: Does this tool require permanent logging? (Usually yes).

## Execution Blocking
If an LLM hallucinates a tool that does not exist, or attempts to call a tool it lacks permissions for, the Framework intercepts the call, prevents execution, and returns a `TOOL_ERROR: UNAUTHORIZED` back to the LLM, giving it a chance to self-correct its plan.
