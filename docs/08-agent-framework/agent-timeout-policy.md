# Agent Timeout Policy

Timeouts ensure that an Agent Execution does not hang indefinitely, blocking resources and inflating compute costs.

## Timeout Boundaries

The Framework enforces timeouts at multiple levels:

1. **Model Timeout**: The maximum time allowed for a single API call to the LLM (e.g., 60 seconds). A timeout here triggers a `MODEL_ERROR` and a retry.
2. **Tool Timeout**: The maximum time allowed for a single sandbox tool execution. E.g., a unit test tool might have a 5-minute timeout.
3. **Execution Timeout**: The maximum wall-clock time allowed for the entire atomic execution, from `ASSIGNED` to `COMPLETED`. If this hits, the Execution is forcibly terminated.
4. **Task Timeout**: The SLA defined by the Workflow Engine for the entire Jira ticket. (Can span multiple executions/retries).
5. **Approval Timeout**: If an agent is `WAITING_FOR_APPROVAL` for 48 hours without a human response, the task transitions to `CANCELLED`.

## Deterministic Behavior
Timeouts must be deterministic. If a timeout occurs, the Framework must tear down the tool sandbox, gracefully cancel the LLM HTTP request, and write a deterministic `TIMEOUT` error to the audit log. Uncaught timeout exceptions that leave orphaned Docker containers are unacceptable.
