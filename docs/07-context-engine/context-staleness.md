# Context Staleness

When a retrieved candidate violates the [Freshness](context-freshness.md) policy, it is considered stale. Stale context is dangerous because it leads to "hallucinations of the past."

## Stale Context Handling

When the Context Engine identifies a stale item, it must execute one of the following actions:

1. **Refreshed**: The engine intercepts the stale cache hit and triggers a live rebuild from the source system.
2. **Excluded**: If a live rebuild fails (e.g., the source system is down), and the information is `HIGH` or `CRITICAL` priority, the context is excluded and the task may fail closed.
3. **Explicitly Presented as Historical**: If the stale information is `NORMAL` or `LOW` priority (e.g., an old conversation summary), it can be included, but it must be heavily annotated. 
   - *Example*: `[WARNING: This conversation occurred 3 months ago and may not reflect current project state]`.
4. **Marked Stale**: The UI or Audit Log flags that the agent operated on degraded, stale context.
