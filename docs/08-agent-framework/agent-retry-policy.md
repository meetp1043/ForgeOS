# Agent Retry Policy

The Retry Policy dictates how the Framework handles transient failures without endlessly burning compute budgets or causing destructive loops.

## Retryable vs. Non-Retryable
Only specific error classifications (e.g., `MODEL_ERROR`, `RATE_LIMIT`) are handled by the Framework's outer retry loop.

**Never blindly retry:**
- Database destructive operations (if they fail, state is likely corrupted).
- Production deployments.
- Credential changes.
- `SECURITY_ERROR`s.

## Retry Mechanics
For retryable errors, the Framework implements:
1. **Maximum Attempts**: Configurable per task (e.g., default `3`).
2. **Exponential Backoff**: Wait times increase between failures.
3. **Jitter**: Randomization added to prevent thundering herds on rate limits.
4. **State Reset**: Before a retry, the workspace must be reverted to a clean state (e.g., `git reset --hard`) to prevent the agent from compounding syntax errors.

## Agent Self-Correction
The outer Framework retry loop is distinct from the agent's internal *self-correction loop*. If a compiler throws a `TOOL_ERROR`, the LLM is fed the error and given a chance to fix its own code. This internal loop is governed by the `Token Budget` and `Timeout` rather than a simple counter.
