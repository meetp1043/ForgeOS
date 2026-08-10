# Workflow Idempotency

Idempotency ensures that executing an operation multiple times produces the same result as executing it once. This is critical for a durable workflow engine that retries operations.

## Idempotency Classification

| Operation | Idempotent? | Strategy |
| :--- | :--- | :--- |
| Creating a file | Yes | Overwrite produces the same result. |
| `git commit` | Conditionally | Use `--allow-empty` or check for changes first. |
| Database `INSERT` | No | Use upsert or check-before-insert with idempotency keys. |
| Database `DROP TABLE` | No | **Never retry.** |
| Cloud resource creation | Conditionally | Use provider idempotency tokens (e.g., AWS `ClientToken`). |
| Deployment | No | **Never auto-retry.** Escalate for human decision. |
| Sending a notification | No | Use deduplication keys to prevent double-sends. |
| External API call | No | Use idempotency keys if the API supports them. |

## Idempotency Keys
For non-idempotent operations that must be retried, the engine generates a unique idempotency key at the time of the first attempt. If the step is retried, the same key is sent, allowing the downstream system to deduplicate.

## Engine-Level Deduplication
The workflow engine itself maintains a deduplication table to prevent the same step instance from being dispatched twice during recovery. Before dispatching a step, the engine checks: "Has this exact step instance ID with this exact attempt number already been dispatched?" If yes, it skips the dispatch.
