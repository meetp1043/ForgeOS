# Storage Strategy

ForgeOS must securely and durably persist memory. The storage strategy favors simplicity and operational maturity over chasing the latest infrastructure trends.

## Core Principle
Prefer minimizing infrastructure complexity. Do not introduce a dedicated vector database (like Pinecone, Milvus, or Qdrant) unless the scale of embeddings explicitly justifies it.

## The Primary Solution: PostgreSQL + pgvector
For the initial ForgeOS implementation, PostgreSQL equipped with the `pgvector` extension is the standard.

### Why PostgreSQL?
1. **ACID Compliance**: Crucial for tracking Memory Updates, Ownership, and Permissions securely.
2. **Relational Ties**: Memory entries heavily reference other entities (TenantID, ProjectID, WorkflowID). A relational database handles these constraints flawlessly.
3. **Hybrid Search**: PostgreSQL handles both exact-match keyword searching and vector similarity (via `pgvector`) in a single query, making [Hybrid Search](memory-hybrid-search.md) implementation much easier than syncing between a relational DB and an external vector DB.
4. **Security**: Row-Level Security (RLS) can be utilized to enforce Tenant and Project scoping at the lowest level.

## Handling the Payload
- **Structured Metadata**: Confidence, Importance, Scope, and Timestamp are stored as standard relational columns.
- **Content**: The raw memory payload (which may be large) is stored as `TEXT` or `JSONB`.
- **Embeddings**: Stored in a `vector` column alongside the row, ensuring atomicity.

## Alternative Evaluations (For Future Scale)
- **Object Storage (S3)**: If raw payloads (like full transcribed chats or huge artifacts) exceed database limits, the DB should store a pointer to an Object Storage bucket.
- **Dedicated Vector DB**: Only evaluated if vector search latency becomes a bottleneck under massive enterprise load, requiring distributed HNSW indexing beyond what PostgreSQL can comfortably manage.
