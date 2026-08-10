# Agent Error Model

Errors during execution are inevitable. The Framework classifies errors to determine whether to retry, escalate, or fail the task.

## Error Classifications

- **MODEL_ERROR**: The LLM returned invalid JSON, timed out, or hallucinated a non-existent tool. (Retryable).
- **TOOL_ERROR**: The executed tool failed (e.g., compiler error, test failure). (Agent must self-correct; Framework retries loop).
- **CONTEXT_ERROR**: The Context Engine failed to assemble the package (e.g., missing critical security policy). (Fatal, Escalate).
- **MEMORY_ERROR**: The agent attempted an unauthorized write to the Memory Engine. (Fatal, Audit).
- **PERMISSION_ERROR**: The agent attempted to invoke a tool it lacks rights for. (Fatal, Escalate).
- **VALIDATION_ERROR**: The agent claimed success but failed the Validation Policy. (Retryable).
- **TASK_ERROR**: The task requirements are logically impossible. (Fatal, Escalate).
- **SECURITY_ERROR**: Prompt injection detected, or dangerous sandbox escape attempted. (Fatal, Suspend Agent, Alert Human).
- **TIMEOUT**: The wall-clock limit was reached. (Fatal).
- **BUDGET_EXCEEDED**: The token or cost limit was hit. (Fatal, Escalate for budget increase).
- **DEPENDENCY_ERROR**: Required external systems (e.g., Jira, GitHub) are down. (Retryable with backoff).
- **INFRASTRUCTURE_ERROR**: The Tool Sandbox failed to provision. (Retryable).
- **RATE_LIMIT**: Provider API limits hit. (Retryable with backoff).
- **UNKNOWN_ERROR**: Unhandled exception. (Fatal).

## Error Handling Matrix
Every error maps to a deterministic behavior profile (Retryable?, User-Visible?, Audit Severity?).
