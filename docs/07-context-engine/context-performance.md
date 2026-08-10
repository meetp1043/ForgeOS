# Performance

The Context Engine sits directly in the critical path of agent execution. If building the context takes 30 seconds, the agent feels slow and unresponsive.

## Performance Considerations

The Context Engine must optimize:
1. **Retrieval Latency**: Querying PostgreSQL (`pgvector`) must be indexed and fast. External API calls (like fetching Jira tickets) must have strict timeouts.
2. **Ranking Latency**: The ranking algorithm (evaluating Authority, Relevance, Freshness) should execute in milliseconds in-memory, avoiding N+1 database queries.
3. **Repository Analysis**: Walking a massive AST or file tree in real-time is too slow. This must be a background process, with the Context Engine querying pre-computed dependency graphs.
4. **Embedding Lookup**: Utilizing the [Cache](context-cache.md) to avoid calling the external embedding API for frequently asked questions.
5. **Summarization**: Calling an LLM to summarize a document during the critical path adds massive latency. Summarization should be pre-computed asynchronously when possible.
6. **Context Assembly**: The final string concatenation and token counting must be highly optimized.

## The Lazy Retrieval Principle
**The Context Engine should avoid expensive operations when unnecessary.** 
If a simple task requires only one specific file, do not spin up a multi-threaded vector search across the entire memory database just to see if anything else *might* be relevant.
