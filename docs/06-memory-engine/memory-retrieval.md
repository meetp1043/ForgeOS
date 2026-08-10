# Memory Retrieval

Retrieval is the mechanism by which stored memory is recalled and injected into an agent's working context. Because agents cannot load the entire database into their prompt window, retrieval must be highly selective, accurate, and secure.

## The Retrieval Pipeline

When an agent needs context, the Memory Engine executes the following pipeline:

1. **Query Formulation**: The agent (or orchestrator) formulates a query based on the current task (e.g., "What is the authentication strategy for the frontend?").
2. **Candidate Generation**: The storage layer fetches a broad set of potential matches using Hybrid Search (Keywords + Vectors).
3. **Permission Filtering**: All candidates outside the agent's explicit Scope, Tenant, or Access Policy are strictly dropped.
4. **Relevance Filtering**: Candidates that do not meet a minimum semantic or keyword match threshold are dropped.
5. **Ranking**: The remaining candidates are scored and ordered based on Authority, Recency, Confidence, and Relevance (see [Memory Ranking](memory-ranking.md)).
6. **Confidence Evaluation**: Low-confidence memories that conflict with high-confidence memories are excluded.
7. **Deduplication**: Semantically identical memories (e.g., the same fact extracted from three different chats) are collapsed into a single representation.
8. **Context Assembly**: The final, curated list of facts and decisions is formatted (often as a bulleted list or JSON block) and injected into the agent's prompt.

## Constraints
- **Do not assume vector similarity alone is sufficient.** Pure vector search often retrieves conceptually similar but factually irrelevant data.
- **Fail Closed**: If the retrieval engine cannot verify permissions on a memory, it must drop it.
