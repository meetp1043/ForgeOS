# Memory Cost Control

Memory systems, particularly those relying on vector embeddings and continuous LLM processing (like Summarization), can quickly spiral in cost. 

ForgeOS must actively constrain the operational cost of the Memory Engine.

## Cost Drivers & Mitigations

### 1. Embedding Generation
- **Cost**: Sending raw text to an embedding model API costs tokens. 
- **Mitigation**: Do not embed everything blindly. Filter out temporary noise, debug logs, and short-lived workflow states before generating embeddings. Use [Summarization](memory-summarization.md) to compress large blocks of text *before* embedding.

### 2. Retrieval Context Windows
- **Cost**: Injecting massive memory payloads into an agent's prompt increases the cost of every single inference call.
- **Mitigation**: Enforce aggressive [Compression](memory-compression.md) and strict top-K limits during Retrieval.

### 3. Re-indexing
- **Cost**: If the chosen embedding model is upgraded or changed, the entire database might need to be re-embedded.
- **Mitigation**: Avoid frequent model swaps. Only re-index if evaluation metrics show a critical drop in semantic retrieval accuracy.

### 4. Storage & Long-Term Retention
- **Cost**: Storing terabytes of redundant memory across PostgreSQL and cache layers.
- **Mitigation**: Enforce [Expiration](memory-expiration.md) and [Deletion](memory-deletion.md) policies ruthlessly.

## Utilizing Cheaper Strategies
Whenever possible, the system should use cheaper keyword search (BM25) over expensive semantic search. For instance, finding a specific user preference by `UserID` should never require a vector search.
