# Redis Boundary

Redis is utilized alongside PostgreSQL, but strict boundaries exist.

## Permitted in Redis
- **Caching**: Storing frequently accessed, slowly changing records (e.g., Agent Definitions).
- **Session/Token State**: JWT blacklists or temporary user sessions.
- **Execution State**: Highly volatile, temporary state during a running agent loop (e.g., the current working prompt string) to avoid crushing Postgres with thousands of updates per minute.
- **Locks**: Distributed locks for workflow orchestration.

## Forbidden in Redis
- **System of Record**: Redis must never be the authoritative source for any relational data. If Redis is flushed, the system must be fully recoverable from PostgreSQL.
