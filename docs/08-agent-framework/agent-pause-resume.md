# Agent Pause & Resume

Agents often need to yield execution for external events (e.g., waiting for an integration test pipeline to finish, or waiting for Human approval on a critical deployment).

## Pause Mechanics
When an agent pauses:
1. It stops generating new tokens.
2. It stops initiating new tool calls.
3. Its volatile state (current prompt context, execution history) is serialized and stored durably in the database.
4. The CPU/Thread resources are released back to the ForgeOS pool.
5. The state transitions to `PAUSED` or `WAITING_FOR_APPROVAL`.

## Resume Mechanics
When the blocking event clears:
1. The Framework re-allocates a thread.
2. The serialized state is reloaded.
3. **Critical Step**: The Framework re-validates permissions and context freshness. (If the agent was paused for 3 days waiting for approval, its token might have expired, or the underlying ADR might have changed).
4. If the context is stale, the Framework may force a Context Refresh before resuming the LLM.
5. The LLM is provided a system message indicating it was paused and has now resumed, along with the result of the blocking event (e.g., "Human approved the deployment").
