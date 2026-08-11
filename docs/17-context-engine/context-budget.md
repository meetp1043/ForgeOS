# Context Budgeting & Allocation

A core feature of the Context Engine is maintaining a strict token budget defined by the `ModelGateway`.

When building a `ContextPack`, the engine follows these rules:
1. All `SYSTEM_POLICY` and `CRITICAL` importance items are added first.
2. The remaining items are ranked by their `relevanceScore`.
3. The engine iteratively adds items into the context until the estimated token limit (e.g. 32k or 128k) is reached.
4. Any remaining items are **truncated** from the context entirely.

This guarantees that Agents always receive the most critical structural instructions, even if a user queries for an absurdly large amount of file data.
