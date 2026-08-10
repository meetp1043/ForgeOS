# Transaction Strategy

Transactions are managed at the **Use Case / Service Layer** using Spring's `@Transactional`.

## Critical Boundaries
1. **No Network Calls inside Transactions**: 
   - You MUST NOT hold a database transaction open while calling OpenAI, GitHub, or any other external API.
   - *Why?* A slow API call (e.g., a 30-second LLM generation) will hold the database connection open for 30 seconds. This rapidly exhausts the HikariCP connection pool, bringing down the entire OS.
2. **Transaction Size**: Transactions should be short-lived, primarily wrapping single CRUD operations or tight batch inserts.
