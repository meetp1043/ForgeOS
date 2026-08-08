# Data Architecture

ForgeOS utilizes a polyglot persistence strategy, matching the database technology to the data access pattern.

## 1. PostgreSQL (Relational)
PostgreSQL is the primary transactional store. It holds structured data requiring ACID compliance.
- **Domain Entities**: Users, Tenants, Projects, Workspaces, Tasks, Approvals, Billing.
- **Isolation**: Row-Level Security (RLS) can be used to enforce tenant isolation at the database level.

## 2. MongoDB / Document Store
MongoDB is used for large, flexible, or unstructured data that does not fit neatly into relational tables and is frequently queried as a complete aggregate.
- **Domain Entities**: Agent configuration profiles, parsed unstructured PRDs, raw execution logs (if not using an external log sink).

## 3. Vector Database (pgvector or dedicated)
Used by the Context Engine for semantic search and Retrieval-Augmented Generation (RAG).
- **Domain Entities**: Embeddings of past architectural decisions, codebase chunks, and documentation.

## 4. Redis (Cache and Coordination)
- **Domain Entities**: User sessions, rate limiting counters, distributed locks (preventing two agents from editing the same file simultaneously), and short-lived caching of LLM responses.
