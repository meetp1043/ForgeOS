# Context Cache

Context assembly is expensive. It involves database queries, vector searches, permission evaluations, and potentially LLM calls for summarization. The Context Engine utilizes caching to bypass this latency when possible.

## Potential Cached Data
- **Repository Analysis**: The dependency graph of the codebase.
- **Document Summaries**: Pre-computed summaries of large PRDs or ADRs.
- **Embeddings**: Vector representations of queries or snippets.
- **Project Metadata**: Active constraints and policies.
- **Context Fragments**: Fully assembled blocks (e.g., the standard "Security Policy" block injected into every agent prompt).

## Mandatory Cache Boundaries
A cache hit is useless if it violates security. The Context Cache must intrinsically respect:
- **Tenant ID**: The cache key must contain the tenant.
- **Project ID**: The cache key must contain the project.
- **Permissions**: A cached summary of an internal document must still undergo an authorization check to ensure the requesting agent is permitted to read it.
- **Freshness**: The cached object must not outlive the volatility of its underlying source.
