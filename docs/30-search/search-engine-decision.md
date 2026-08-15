# SEARCH ENGINE DECISION

## Selected Technology
**PostgreSQL Full-Text Search (FTS) + pgvector**

## Why Selected
ForgeOS already uses PostgreSQL as its primary transactional database. By leveraging PostgreSQL's built-in Full-Text Search capabilities combined with the `pgvector` extension for semantic embeddings, we can deliver a robust hybrid search solution without introducing the operational complexity and cost of a dedicated search cluster (like Elasticsearch or OpenSearch) prematurely.

This adheres to Phase 30 Rule 44: "Do NOT introduce a new database simply because it is popular."

## Alternatives Considered
- **OpenSearch / Elasticsearch**: Excellent for massive scale and advanced text analysis, but introduces significant operational overhead, separate data syncing loops, and higher infrastructure costs.
- **Meilisearch / Typesense**: Great developer experience and fast, but adds another infrastructure component to manage.
- **MongoDB Atlas Search**: N/A as ForgeOS uses PostgreSQL.

## Trade-offs
- **Scaling**: PostgreSQL FTS scales vertically and through read replicas, which is sufficient for most SaaS platforms until they hit extreme scale. Dedicated search engines scale horizontally more easily.
- **Security**: Data never leaves the Postgres boundary, simplifying compliance and security models.
- **Cost**: No additional database clusters to host.
- **Developer Experience**: Developers only need a Postgres container (with pgvector) locally.

## Future Semantic Search Support
`pgvector` natively supports exact and approximate nearest neighbor search (HNSW, IVFFlat), making it fully capable of supporting the Phase 30 Hybrid Search requirements out of the box.
