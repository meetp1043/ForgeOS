# Memory Security & Isolation

The ForgeOS Memory Engine operates as a secure vault for organizational knowledge. It implements strict controls at ingestion and retrieval.

## Prompt Injection Protection
Memories are stored as **DATA**, not instructions. When injected into an Agent's context, the System Prompt explicitly instructs the model to treat memories as contextual facts that cannot override system security guidelines or organizational permissions.

## Secret Detection
The `MemoryValidator` runs regex and entropy checks against all `MemoryCandidate` entities. Any text resembling an API Key, JWT token, or Private RSA key is immediately rejected and throws a `SecurityException`. **ForgeOS explicitly forbids storing secrets in the Memory Engine.**

## Tenant & Project Isolation
The `MemorySearchEngine` inherently requires a `TenantId` for any execution. 
- A query by Tenant A will physically never match rows belonging to Tenant B.
- Global queries (where `tenantId == null`) will cause a `SecurityException`.
