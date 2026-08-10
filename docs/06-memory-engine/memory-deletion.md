# Memory Deletion

While the update policy dictates that important memories are superseded rather than overwritten, there are strict scenarios where memory must be permanently and irreversibly deleted.

## Deletion Triggers

1. **User Deletion**: A user explicitly requests the deletion of their user-scoped memory (e.g., preferences, history). This is a hard privacy requirement.
2. **Project Deletion**: When a project is torn down, all memory strictly scoped to that project must be cascade-deleted.
3. **Organization Deletion**: Offboarding a tenant requires wiping all organizational, project, and user memory associated with that tenant.
4. **Retention Expiration**: Memories with a short-term TTL that are not flagged for archive must be garbage collected.
5. **Privacy Requests**: Standard GDPR/CCPA data subject access requests requiring the purging of PII.
6. **Security Deletion**: If a secret, credential, or highly sensitive payload is accidentally committed to memory, a security admin (human or specialized agent) must hard-delete it immediately.

## Cascading Implications
When a memory is deleted, the system must handle dangling references:
- If Memory B `SUPERSEDES` Memory A, and Memory A is deleted, Memory B's relationship graph must be safely updated.
- Vector embeddings associated with the deleted memory must be purged from the index to prevent semantic retrieval.
- Delete operations generate a minimal, sanitized audit log (e.g., "Memory UUID deleted due to Privacy Request") without retaining the deleted payload.
