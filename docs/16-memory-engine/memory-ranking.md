# Memory Ranking Formula

When the `MemorySearchEngine` executes a query, it may retrieve dozens of potential matches via Keyword (TSVECTOR) or Semantic (pgvector) indexing.

To determine which memories are passed into the limited context window of an Agent, the engine applies a ranking formula that considers:

1. **Semantic Similarity**: How closely the memory matches the query's embedding vector.
2. **Authority**: Multiplier applied based on `MemoryAuthority`. `HUMAN_APPROVED` (x1.5) > `VERIFIED_TOOL_RESULT` (x1.2) > `MODEL_GENERATED` (x1.0).
3. **Importance**: Multiplier applied based on `MemoryImportance`. `CRITICAL` > `HIGH` > `NORMAL`.
4. **Recency**: A slight time-decay curve ensures newer memories break ties against older memories, but recency alone cannot override a high-authority fact.
5. **Scope**: Exact `workspace` or `project` matches heavily outrank generic `organization` wide conventions.
