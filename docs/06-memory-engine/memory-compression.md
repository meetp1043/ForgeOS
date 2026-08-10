# Memory Compression

Memory Compression works hand-in-hand with Summarization, but refers specifically to the technical optimization of the context payload injected into the agent's prompt.

## Context Assembly Compression

Even after retrieval, ranking, and summarization, the resulting memory payload might be too large. 

1. **Truncation**: If the token budget is strictly limited, lower-ranked memories are hard-dropped.
2. **Key-Value Extraction**: Instead of injecting full paragraphs, the Context Engine may extract just the key-value pairs (e.g., converting a 300-word ADR into `{"Database": "PostgreSQL", "Reason": "Relational integrity"}`).
3. **Reference Linking**: Instead of injecting the full text of an Artifact Memory, the system injects a reference (e.g., `See ADR-005 for Database constraints`), allowing the agent to explicitly use a tool to read the artifact if it deems it necessary.

Compression ensures that the Memory Engine respects the strict token limits and cost controls of the broader ForgeOS system.
