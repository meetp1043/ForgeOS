# Context Recovery

If the Context Engine experiences a severe failure (e.g., [Validation](context-validation.md) fails due to missing critical security policies), it must attempt to recover the state.

## Recovery Strategies

1. **Retry**: If the failure was due to a transient network timeout with a backend source (e.g., the Artifact store), retry with exponential backoff.
2. **Rebuild**: If a cached Context Package is found to be corrupted or invalid, clear the cache and rebuild from scratch.
3. **Fallback Retrieval**: Switch from Vector Search to Keyword Search if the embedding model times out.
4. **Fallback Model**: If the primary model rejects the Context Package due to token limits despite compression, request the Model Router to try an alternate model with a larger window.
5. **Reduced Context**: If all else fails, aggressively drop all `LOW` and `NORMAL` priority context items and attempt to assemble a bare-bones `CRITICAL`-only package.
6. **Workflow Pause & Human Escalation**: If critical context (like deployment credentials or mandatory approval records) simply cannot be found or validated, the Context Engine signals the Workflow Engine to pause the task and alert a human operator. 

**Do not silently continue with incomplete critical context.**
