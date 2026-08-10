# Context Integration

The Context Engine provides the data; the Agent Framework consumes it. 

## Integration Flow

1. **Request**: The Agent Instance initializes and constructs a `ContextRequest`. It includes its `Context Policy` (e.g., "I need the backend code and the database schema"), its Tenant ID, and its Permissions.
2. **Retrieval**: The Context Engine does the heavy lifting of vector search, deduplication, and security filtering.
3. **Delivery**: The Context Engine returns a `Context Package` formatted optimally for the target LLM.
4. **Injection**: The Agent Framework injects this package into the system prompt.

## Boundary Enforcement
The Agent Framework implicitly trusts the Context Package provided by the Context Engine, but the Framework is still responsible for enforcing that the agent does not use that context to violate tool permissions.

*Crucial Rule*: Context does not grant permissions. Just because the Context Engine provided the agent with the production database schema does not mean the Agent Framework will allow the agent to execute a tool that drops tables.
