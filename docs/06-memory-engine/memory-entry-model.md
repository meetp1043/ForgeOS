# Conceptual Memory Entry Model

A memory entry is the fundamental unit of stored knowledge in ForgeOS. This is a conceptual schema; physical database schemas (e.g., SQL tables or JSON documents) are implementation details left to the persistence layer.

## Attributes

| Attribute | Type | Description |
| :--- | :--- | :--- |
| `MemoryID` | UUID | Unique identifier for the memory entry. |
| `Type` | Enum | The category of memory (e.g., Semantic, Episodic, Procedural, Decision). |
| `Content` | Text | The core information or knowledge payload. |
| `Summary` | Text | A concise, semantic representation of the content for fast embedding and retrieval. |
| `Source` | Reference | A pointer to where this knowledge originated (e.g., a specific Chat ID, Tool Run ID, or Artifact ID). |
| `Scope` | Object | The boundaries within which this memory applies (Tenant ID, Project ID, Workspace ID, User ID, Agent ID). |
| `Timestamp` | DateTime | When the memory was created or last updated. |
| `Confidence` | Enum | The certainty of the information (`HIGH`, `MEDIUM`, `LOW`). |
| `Importance` | Integer | A heuristic score determining how critical this memory is to project success. |
| `Sensitivity` | Enum | Security classification (e.g., `PUBLIC`, `INTERNAL`, `RESTRICTED`). |
| `Tags` | List[String] | Metadata tags for fast filtering (e.g., `architecture`, `frontend`, `auth`). |
| `Relationships` | List[UUID] | Pointers to related MemoryIDs (e.g., "Supersedes Memory X"). |
| `Version` | Integer | Iteration tracker for updates. |
| `Expiration` | DateTime | Optional TTL for transient memories. |
| `AccessPolicy` | Object | RBAC/ABAC rules defining who or what can read this entry. |
| `Provenance` | Object | Cryptographic or auditable chain of custody detailing the exact actor (Agent/Human) that validated the entry. |

*Note: This model intentionally avoids physical schema definitions to remain agnostic to underlying storage (PostgreSQL, DocumentDB, etc.).*
