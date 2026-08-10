# Memory Consistency

The Memory Engine deals with different types of data, some of which require strict consistency, while others can tolerate eventual consistency.

## Strong Consistency
Important, approved decisions (like Decision Memory or Procedural Memory) require **Strong Consistency**. 
- If a human user explicitly updates an Architecture Decision to "Use PostgreSQL", the very next agent task assigned must immediately retrieve this updated fact. 
- The relational database (PostgreSQL) provides this guarantee for the core memory entries.

## Eventual Consistency
Analytical features and search indexes may rely on **Eventual Consistency**.
- When a memory is added, the background job to generate its vector embedding (calling the LLM API) may take seconds.
- The memory is immediately available via Keyword search (Strong Consistency), but may not appear in a Semantic Vector search until the embedding is generated and indexed (Eventual Consistency).

## Concurrency and Updates
When multiple agents attempt to update the same memory entry concurrently:
- Optimistic Concurrency Control (OCC) is enforced using the `Version` attribute.
- If Agent A and Agent B try to update Version 1 simultaneously, Agent A will succeed (creating Version 2), and Agent B will receive an error, forcing it to re-read Version 2 and re-evaluate its action.
