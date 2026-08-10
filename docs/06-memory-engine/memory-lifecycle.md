# Memory Lifecycle

Every memory entry in ForgeOS follows a strict lifecycle. This ensures that information is not blindly committed to long-term storage without validation, and that stale information is properly aged out. Every important transition is auditable.

## Lifecycle States

1. **Candidate**: Information has been extracted (e.g., from a conversation or a tool output) but has not yet been verified as accurate, useful, or novel.
2. **Created**: A candidate is drafted into a standard Memory Entry structure.
3. **Validated**: The entry is checked against existing memory (for conflicts), security rules (for secrets), and scope boundaries.
4. **Stored**: The entry is written to the persistent storage layer.
5. **Indexed**: The entry is embedded (if applicable) and added to search indexes for retrieval.
6. **Retrieved**: The entry is actively fetched and injected into an agent's working memory context.
7. **Updated**: The entry's content, confidence, or metadata is modified (creates a new version; history is preserved).
8. **Superseded**: The entry is explicitly replaced by a newer, more authoritative decision or fact. It remains in storage for historical audit but is hidden from default retrieval.
9. **Archived**: The entry has reached the end of its active relevance but is retained for compliance or long-term historical context.
10. **Expired**: A time-to-live (TTL) boundary is reached. The entry is flagged for cleanup.
11. **Deleted**: The entry is permanently removed from the system. (See [Memory Deletion](memory-deletion.md)).
12. **Corrected**: An erroneous entry is explicitly fixed by a user or authorized agent (creates an audit trail of the correction).

## Audit Trail
Every transition from `Validated` onwards generates an audit event. This guarantees traceability: if an agent makes a decision based on memory, human operators can trace exactly when and how that memory was formed.
