# Memory Privacy

While Security protects against malicious attacks, Privacy protects the rights and data minimization requirements of the users.

## Core Privacy Principle
**Do not store information simply because it might be useful later.** 

If information is not actively required for a defined workflow, project, or explicit user preference, it must not be retained in the Memory Engine.

## Privacy Controls

1. **Data Minimization**: 
   - Agents must strip Personally Identifiable Information (PII) from conversational summaries before committing them to Semantic Memory, unless that PII is the explicit subject of the workflow (e.g., an HR application).

2. **User Visibility**:
   - Users must be able to view, query, and audit the [User Memory](memory-user-memory.md) stored about them. The dashboard must provide a clear interface showing "What ForgeOS knows about you."

3. **Deletion (Right to be Forgotten)**:
   - Users can execute a hard delete of their User Memory. This operation must bypass standard retention/archival safeguards and physically remove the rows.

4. **Retention limits**:
   - Adhere to the [Memory Retention](memory-retention.md) and [Expiration](memory-expiration.md) policies to ensure data does not outlive its legal or practical utility.

5. **Audit Logs**:
   - Access to user-scoped memory by an agent generates an audit log, allowing privacy compliance officers to verify that data is only accessed when necessary.
