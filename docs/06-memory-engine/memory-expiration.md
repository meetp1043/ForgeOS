# Memory Expiration

Some information naturally becomes stale and loses its value. Stale information, if retrieved by an agent, can lead to hallucinations, incorrect architectural choices, or failed deployments.

## The Concept of Stale Memory
Memory expiration addresses the problem of temporal irrelevance.
- A temporary model configuration used for testing should not be retrieved next month.
- A deployment status of `IN_PROGRESS` is meaningless three days later.

## Expiration Mechanics

### TTL (Time-To-Live)
At creation, specific memory types (like Episodic or Workflow memory) are assigned a TTL in their `Expiration` attribute.
Once `Timestamp.now() > Expiration`:
1. The memory is marked `Expired`.
2. It is immediately excluded from standard RAG and semantic retrieval pipelines.

### Audit Considerations
Expired memory should not automatically disappear (hard delete) without considering audit requirements.
- An expired workflow state might still be needed for a post-mortem incident investigation.
- **Action**: Expired memory is moved to "Cold Storage" or flagged `Archived` rather than instantly deleted, unless its Retention Policy (see [Memory Retention](memory-retention.md)) dictates a hard delete.

### Agent Interactions
Agents must not manually manipulate expiration timestamps to bypass retention limits. Expiration rules are enforced by the core Memory Engine layer.
