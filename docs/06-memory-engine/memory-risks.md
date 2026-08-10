# Memory Risks

The design and operation of the Memory Engine carry significant risks. This document catalogs known failure modes and their structural mitigations.

| Risk | Description | Mitigation Strategy |
| :--- | :--- | :--- |
| **Memory Poisoning** | Malicious users or compromised agents injecting false information to skew future actions. | Enforce [Confidence](memory-confidence.md) and [Authority](memory-ownership.md) rules. Restrict write access. |
| **False Memories / Hallucination** | Agents generating incorrect facts and committing them as memory. | Strict Candidate Validation. Widespread use of the [Correction](memory-correction.md) workflow. |
| **Stale Information** | Obsolete decisions being retrieved and treated as active policy. | [Update Policy](memory-update-policy.md) (Supersession) and time-based [Expiration](memory-expiration.md). |
| **Context Pollution** | Retrieving highly relevant but useless information (e.g., debug logs) that swamps the prompt. | Aggressive [Summarization](memory-summarization.md) and [Relevance](memory-relevance.md) filtering. |
| **Privacy Leakage** | Retaining and exposing PII inappropriately. | [Privacy Policies](memory-privacy.md), user deletion rights, and data minimization. |
| **Tenant Leakage** | Tenant A seeing Tenant B's data due to a query bug. | Hard RLS in the database. Mandatory TenantID pre-filtering. |
| **Storage Explosion** | The database growing infinitely due to untruncated episodic logs. | [Retention](memory-retention.md) limits based on data type. |
| **Embedding Cost** | Massive API bills from embedding temporary/useless data. | Stop embedding everything. Use Keyword search for precise lookups. |
| **Conflicting Decisions** | Two opposing architecture rules appearing in the same context window. | Explicit [Conflict Resolution](memory-conflict-resolution.md) hierarchy in the retrieval engine. |
