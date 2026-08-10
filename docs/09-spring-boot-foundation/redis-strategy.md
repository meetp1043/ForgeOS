# Redis Strategy

Redis is utilized as a secondary data store for ephemeral or high-velocity data.

## Use Cases
1. **Caching**: Frequently accessed Agent Definitions or Context Policies.
2. **Execution State**: Storing the highly volatile inner-loop state of an Agent Execution (e.g., the current prompt context window) to avoid hammering PostgreSQL on every LLM token generation.
3. **Coordination**: Distributed locks to prevent two Workflow instances from picking up the same task simultaneously.
4. **Rate Limiting**: Enforcing API limits on LLM provider calls.
