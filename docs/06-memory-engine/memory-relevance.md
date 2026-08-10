# Memory Relevance

Relevance dictates whether a retrieved memory actually helps the agent solve its current task. Irrelevant memory is context pollution, which degrades LLM reasoning and increases costs.

## Evaluating Relevance

Relevance is typically measured during the **Candidate Generation** and **Ranking** phases of retrieval.

### Metrics of Relevance
- **Semantic Overlap**: Does the memory address the same concepts as the prompt?
- **Entity Matching**: Do the specific nouns (e.g., class names, file paths, user IDs) match?
- **Task Alignment**: Is the memory structurally useful for the *type* of task? (e.g., A procedural memory is highly relevant for a "plan execution" task, but less relevant for a "summarize document" task).

## The Danger of Pure Vector Relevance
Relying solely on vector embeddings for relevance is a known anti-pattern.
- *Query*: "How do I connect to PostgreSQL?"
- *Bad Retrieval*: "We decided NOT to use PostgreSQL." (High semantic similarity, negative factual relevance).

To combat this, ForgeOS uses [Hybrid Search](memory-hybrid-search.md) and requires strict Metadata Filtering before evaluating pure semantic similarity.
