# Failure Recovery

When an agent crashes or is killed by the Framework, the system must recover gracefully to prevent data corruption.

## Workspace Recovery
1. The ephemeral workspace (e.g., the cloned git repository) used by the agent is considered dirty.
2. The Framework executes `git reset --hard` and `git clean -fd` before allowing a retry.
3. If the crash occurred mid-database migration on a test container, the test container is destroyed and recreated.

## State Recovery
1. The Workflow Engine is notified of the `FAILED` state.
2. If the task is retryable, a completely new `Agent Execution ID` is generated.
3. The new execution does not inherit the conversational history (the internal monologue) of the failed execution, as it may contain hallucination loops. It starts fresh.

## Orphaned Sandboxes
A cron job (the `Sandbox Reaper`) constantly monitors for tool execution sandboxes that have outlived their TTL, force-killing them to ensure a Framework crash doesn't leak compute resources.
