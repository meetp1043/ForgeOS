# Workflow Retry Policy

Retry policies define how the workflow engine handles transient failures before giving up.

## Retry Configuration

| Field | Type | Description |
| :--- | :--- | :--- |
| `max_attempts` | Integer | Maximum number of execution attempts (including the first). Default: 3. |
| `backoff_strategy` | Enum | `FIXED`, `LINEAR`, `EXPONENTIAL`. |
| `initial_delay` | Duration | Wait time before the first retry (e.g., 5 seconds). |
| `max_delay` | Duration | Maximum wait time between retries (e.g., 5 minutes). |
| `jitter` | Boolean | If true, adds random variance to prevent thundering herd. |
| `retryable_errors` | List | Error categories that qualify for retry (e.g., `MODEL_TIMEOUT`, `TOOL_TRANSIENT`). |
| `non_retryable_errors` | List | Error categories that must NOT be retried (e.g., `PERMISSION_DENIED`, `BUDGET_EXCEEDED`). |

## Retry Behavior
1. The step fails with an error.
2. The engine classifies the error against the `retryable_errors` list.
3. If retryable and `attempts < max_attempts`, the step is rescheduled after the computed backoff delay.
4. If non-retryable or retries are exhausted, the step transitions to `FAILED` and triggers escalation.

## Safety Rules
- **Destructive operations** (e.g., `DROP TABLE`, production deployments) must have `max_attempts: 1`. They are never automatically retried.
- **Idempotent operations** (e.g., `git commit --allow-empty`, creating a file) are safe for retry.
- Each retry attempt is logged as a distinct entry in the audit trail.
