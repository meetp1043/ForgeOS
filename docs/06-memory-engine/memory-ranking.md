# Memory Ranking

Once a set of candidate memories passes the permission and relevance filters, they must be ranked. The context window is limited, so only the most critical information should be injected.

## Ranking Factors

The scoring algorithm is a weighted combination of the following factors:

1. **Relevance**: How closely does the memory match the query? (Calculated via Vector Cosine Similarity and Keyword BM25 scores).
2. **Authority**: Was this memory approved by a human or a Principal Architect? (High authority gets a massive multiplier).
3. **Confidence**: Is this a verified fact (`HIGH`) or an inferred guess (`LOW`)?
4. **Recency (Timestamp)**: Newer information is generally preferred over older information, *unless* the older information has a significantly higher Authority score.
5. **Importance**: An intrinsic heuristic score assigned at creation. (e.g., "Use PostgreSQL" is more important than "I prefer dark mode").
6. **Scope Proximity**: A memory scoped specifically to the current `TaskID` ranks higher than a generic `ProjectID` memory.
7. **Artifact Authority**: Memories linked directly to an authoritative Artifact (like an ADR) rank higher than conversational memories.
8. **Supersession**: If Memory B supersedes Memory A, Memory A's rank is reduced to zero (unless historical context was explicitly requested).

## Dynamic Weighting
The weights applied to these factors change based on the User Intent. 
- If the user asks "What did we decide?", Authority and Supersession are weighted highest.
- If the user asks "What error did we see yesterday?", Recency and Relevance are weighted highest.
