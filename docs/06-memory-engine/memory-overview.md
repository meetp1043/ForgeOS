# Memory Overview

## What is ForgeOS Memory?
ForgeOS Memory is defined as **persisted information intentionally retained by ForgeOS for future retrieval and reasoning**. 

It is the mechanism through which the system retains context over long periods, across different tasks, agents, and workflows. It forms the cognitive foundation of the AI organization, allowing it to act on historical precedents, architectural decisions, and known project constraints.

## What Memory Is Not
To maintain strict boundaries, **Memory** must be distinguished from other forms of data retention in ForgeOS:

- **Model Training**: Memory is external to the LLM weights. It is retrieved at runtime (e.g., via RAG or structured queries), not baked into the foundational model.
- **Context**: Context is the temporary assembly of information injected into an agent's prompt during execution. Memory can *become* context when retrieved, but the entirety of memory is never in context at once.
- **Logs**: Logs are append-only records of execution events. Memory is curated, updateable, and deduplicated knowledge.
- **Artifacts**: Artifacts (like code files, PRDs, ADRs) are authoritative products of engineering. Memory *indexes* and *references* artifacts but does not replace them.
- **Events**: Events are point-in-time state changes used by the Workflow Engine.
- **Conversation History**: The raw, turn-by-turn chat transcript. Conversation history is raw data; Memory is the distilled, validated extraction of facts and decisions from that history.

## Why Intentionality Matters
Agents must not automatically remember everything they encounter. 
- **Temporary Noise**: Raw API responses, debug stack traces, and intermediate thought processes should not pollute the memory system.
- **Security & Privacy**: Sensitive credentials, passwords, and PII must never be stored in the generic memory pool.
- **Staleness**: Blindly remembering everything leads to contradictions (e.g., remembering an old architecture decision alongside a new one without clear supersession).

ForgeOS memory is scoped, curated, and explicitly managed.
