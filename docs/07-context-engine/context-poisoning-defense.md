# Context Poisoning Defense

Context poisoning occurs when incorrect or malicious information is intentionally or accidentally introduced into the persistent storage layers (Memory, Code, Artifacts) to skew future agent decisions over time.

## Defenses Against Poisoning

The Context Engine relies on several mechanisms to detect and reject poisoned context during the retrieval and ranking phases:

1. **Provenance Tracking**: Every piece of context is tagged with its origin. If poisoning is suspected, human operators can trace exactly which agent or user committed the bad data.
2. **Authority & Approval Status**: Untrusted, unapproved data (like a random Jira comment) has low Authority. It will be naturally overridden by approved ADRs.
3. **Confidence Scoring**: Information inferred by agents without human validation carries a low confidence score, penalizing its rank in future retrievals.
4. **Versioning**: The Context Engine prefers the most recent version of an artifact, but maintains the ability to query historical versions to detect sudden, suspicious drifts in policy.
5. **Conflict Detection**: The [Conflict Resolution](context-conflict-resolution.md) engine flags when a newly retrieved candidate aggressively contradicts established, high-authority memory.
6. **Auditability**: Every Context Package assembled is logged, allowing security teams to retrospectively hunt for poisoned context payloads.
