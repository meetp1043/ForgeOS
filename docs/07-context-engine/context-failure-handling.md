# Failure Handling

The Context Engine depends on numerous external systems (Memory, Git, Vector DBs, LLM Summarizers). It must handle their inevitable failures gracefully.

## Failure Scenarios & Fallbacks

- **Memory Unavailable**: Skip semantic retrieval. Rely entirely on immediate Git state, explicit task definitions, and fallback to `Degraded Mode` (warn the agent that historical context is missing).
- **Repository Unavailable**: The task is fundamentally impossible. Halt Context Assembly and trigger a fatal error back to the Workflow Engine.
- **Search/Embedding Failure**: Fallback to exact-match keyword (BM25) search or traditional SQL queries.
- **Model Limitation (Token Overflow)**: If compression algorithms fail to bring the context under the token limit, request the Model Router to escalate to a higher-capacity model, or halt.
- **Permission Evaluation Failure (Security System Down)**: **Fail Closed.** Drop all candidates that require authorization.
- **Artifact Unavailable**: If a required PRD or ADR 404s, explicitly include a warning in the Context Package: `[WARNING: Required artifact ADR-005 could not be retrieved.]`
- **External Integration Failure (e.g., Jira API Down)**: Assemble the package using cached data if available and fresh; otherwise, omit the external data and warn the agent.
