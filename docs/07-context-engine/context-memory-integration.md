# Memory Integration

The Context Engine heavily relies on the Memory Engine to provide historical decisions, semantic context, and distilled rules. However, they remain distinct systems.

## The Integration Flow

1. **Context Engine** initiates a retrieval query.
2. **Memory Engine** receives the query and performs a semantic/hybrid search across its persistent store.
3. **Memory Engine** returns a list of *Candidate Memories*.
4. **Context Engine** applies its own context-specific `FILTERING` (e.g., verifying if the agent's role permits viewing these specific memories).
5. **Context Engine** evaluates `RELEVANCE` and `AUTHORITY`.
6. **Context Engine** performs `RANKING` alongside candidates from other sources (like Git or Workflows).
7. **Context Engine** packages the highest-ranking items into the `Context Package`.

## Key Conceptual Difference
- **Memory** remains persistent, global knowledge stored in the database.
- **Context** remains a temporary, task-specific payload injected into the prompt. Memory is just one of many sources that feed into Context.
