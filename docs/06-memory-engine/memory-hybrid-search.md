# Hybrid Search

Because neither Keyword Search (BM25) nor Vector Search (Embeddings) is perfect on its own, ForgeOS employs Hybrid Search to retrieve memory.

## The Hybrid Concept

Hybrid search combines the exact-match precision of keywords with the conceptual understanding of vectors, enveloped by strict metadata filters.

### 1. Metadata Filtering (The Hard Gates)
Before any searching occurs, the database applies hard filters:
- `TenantID == current_tenant`
- `ProjectID == current_project`
- `Expiration > now()`
- `Superseded == false`

### 2. Keyword Search (BM25)
- Finds exact matches for specific code terms, class names, or error codes.
- Excellent for targeted Retrieval.

### 3. Semantic / Vector Search
- Finds conceptually related memories.
- Excellent for broad queries or when the agent doesn't know the exact terminology.

### 4. Reciprocal Rank Fusion (RRF)
The results from the Keyword search and the Vector search are combined using an algorithm like Reciprocal Rank Fusion, which mathematically merges the two ranked lists into a single, highly relevant context payload. This final list is then subjected to ForgeOS's custom [Memory Ranking](memory-ranking.md) logic (Authority, Confidence, etc.).
