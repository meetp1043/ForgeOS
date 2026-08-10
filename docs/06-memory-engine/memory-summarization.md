# Memory Summarization

As memory accumulates—particularly Conversational and Episodic memory—it becomes too vast to efficiently embed, store, and retrieve. Memory Summarization is the process of compressing historical facts into dense knowledge.

## The Summarization Process

1. **Trigger**: A summarization agent runs on a chron schedule or when a specific memory partition (e.g., a long-running chat) exceeds a token threshold.
2. **Extraction**: The agent reads the raw memory entries.
3. **Condensation**: It extracts the core facts, decisions, and constraints, discarding pleasantries, tangents, and transient states.
4. **Replacement**: The raw memory entries are transitioned to `Archived` state.
5. **Creation**: A new, dense Semantic Memory entry is created, referencing the archived entries in its `Provenance`.

## Benefits
- **Cost Reduction**: Fewer tokens required for embeddings and LLM context windows.
- **Latency Reduction**: Smaller databases are faster to query.
- **Clarity**: Dense summaries reduce the cognitive load (and hallucination risk) for execution agents.
