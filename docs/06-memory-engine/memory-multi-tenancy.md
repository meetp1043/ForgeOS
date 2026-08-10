# Memory Multi-Tenancy

ForgeOS is designed to support multiple organizations operating on the same infrastructure. Consequently, the Memory Engine must enforce strict multi-tenancy.

A tenant must **never** be able to access, retrieve, or semantically search another tenant's memory. A failure here is a catastrophic security breach.

## Tenant Isolation Mechanisms

### 1. Tenant ID Injection
Every memory entry is hard-coded with a `TenantID`. The retrieval API requires the `TenantID` to be injected into every query at the infrastructure level, bypassing the application logic entirely where possible (e.g., using Row-Level Security in PostgreSQL).

### 2. Project Boundaries
Within a tenant, data is further partitioned by `ProjectID`. This acts as an internal multi-tenancy layer, preventing Team A from seeing Team B's memory unless explicitly shared.

### 3. Query Filtering
Vector searches and semantic retrievals must apply the `TenantID` as a pre-filter. Post-filtering (retrieving top K results and then filtering out other tenants) is strictly prohibited as it leaks vector space and ruins query efficiency.

### 4. Cache Isolation
In-memory caches (like Redis) storing temporary contexts or retrieval results must namespace all keys by `TenantID`.

### 5. Embedding Isolation
While the foundational embedding model (e.g., text-embedding-ada-002) is shared, the vector database indices should ideally be partitioned by tenant, or utilize robust metadata filtering guaranteed by the database engine.
