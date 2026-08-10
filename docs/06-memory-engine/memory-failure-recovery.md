# Failure Recovery

The ForgeOS system relies heavily on Memory, but it must not completely paralyze if the Memory Engine experiences localized outages.

## Failure Scenarios

### 1. Memory Database Unavailable
- **Impact**: Agents cannot retrieve historical decisions or project context.
- **Recovery Behavior**: The orchestrator pauses non-critical workflows. If a workflow *must* continue, agents operate in "Degraded Mode" (zero historical memory) and must rely strictly on immediate context and explicit user instructions.

### 2. Vector Index Unavailable
- **Impact**: Semantic search fails.
- **Recovery Behavior**: Graceful degradation to Keyword (BM25) search. The system warns the user that search accuracy is degraded.

### 3. Embedding Provider Unavailable (API Down)
- **Impact**: New memories cannot be indexed for vector search.
- **Recovery Behavior**: New memories are stored and indexed for Keyword search. A background queue accumulates the payloads and generates embeddings once the provider API recovers (Eventual Consistency).

### 4. Corrupted Memory / Inconsistent Indexes
- **Impact**: Retrieval returns garbage or cross-contaminated data.
- **Recovery Behavior**: Immediate shutdown of the retrieval API for that tenant. Trigger alert for manual DB restore from the last known good backup.

### 5. Failed Summarization
- **Impact**: Chat histories grow endlessly without being compressed.
- **Recovery Behavior**: The system continues to operate but may hit token limits faster. Alerts are triggered for operators to fix the summarization agent.
