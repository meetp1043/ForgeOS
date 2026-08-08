# Memory Architecture

ForgeOS maintains state across multiple temporal horizons. 

*Note: Storing memory does not mean we are actively training/fine-tuning models. It refers to retrieving historical context to inject into prompts.*

## Memory Tiers
1. **Short-term memory**: The current token window of an active Agent Instance (ephemeral).
2. **Working memory**: The scratchpad and intermediate files an agent generates during a task.
3. **Project memory**: The Vector/Graph database containing all ADRs, PRDs, and resolved conversations for a specific project.
4. **Long-term/Organizational memory**: Cross-project preferences (e.g., "The CTO always prefers TypeScript over JavaScript").
5. **Decision memory**: Explicitly tracked "Why" decisions to prevent agents from revisiting closed debates.

## Architecture
- **Storage**: PostgreSQL (pgvector) or MongoDB for flexible document storage.
- **Summarization**: Background jobs compress old short-term memory logs into dense summaries before moving them to Project memory to save tokens.
- **Isolation**: Memory stores are strictly partitioned by Project ID and Tenant ID.
