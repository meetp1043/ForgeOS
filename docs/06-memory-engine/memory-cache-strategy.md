# Cache Strategy

Memory retrieval can become a bottleneck, especially when generating expensive embeddings for every user query. Caching mitigates this.

## Caching Layers

### 1. Retrieval Cache
- **What**: Caches the final, assembled context payload for a specific, frequently asked query within a specific project.
- **Invalidation**: Invalidated immediately if any memory within that project is updated, superseded, or corrected.

### 2. Embedding Cache
- **What**: Caches the mapping of a specific text string (like a query) to its dense vector representation.
- **Why**: Prevents hitting the external LLM embedding provider repeatedly for the same search query, saving significant cost.
- **Invalidation**: Rarely invalidated, unless the underlying embedding model is swapped.

### 3. Context Assembly Cache
- **What**: Caches the pre-assembled background context for a project (e.g., the standard project rules and ADRs).
- **Why**: Speeds up the initialization of new agent instances.

## Strict Cache Constraints
Caching **must** respect permissions and tenant isolation.
- Cache keys must inherently contain the `TenantID` and `ProjectID`.
- e.g., `cache:tenant_123:project_456:query_embeddings:sha256(query)`
- A cache hit must still undergo a rapid permission authorization check before the payload is returned to the agent.
