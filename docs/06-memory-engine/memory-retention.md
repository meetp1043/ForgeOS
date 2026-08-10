# Memory Retention

ForgeOS must retain memory based on its purpose and classification. Storing everything indefinitely is inefficient, costly, and a privacy liability. 

Retention policies govern how long an entry remains in active storage, archive storage, or before it is permanently deleted.

## Retention Categories

### 1. Temporary / Volatile (Days)
- **Examples**: Temporary task state, intermediate deployment statuses, sub-agent scratchpads.
- **Policy**: Automatically expires and is hard-deleted once the parent task/workflow is completed or canceled.

### 2. Medium-Term Operational (Weeks/Months)
- **Examples**: Workflow execution summaries, test failure patterns, conversation history rollups.
- **Policy**: Retained while actively useful for ongoing project velocity. Archived after 90 days of inactivity.

### 3. Project Lifetime (Years)
- **Examples**: Architecture Decision Records (ADRs), business requirements, domain logic rules, project memory.
- **Policy**: Retained for the entire lifecycle of the project. If the project is deleted, this memory is purged in cascade.

### 4. Long-Term Organizational (Indefinite)
- **Examples**: Company-wide engineering standards, major security policies.
- **Policy**: Retained indefinitely until explicitly superseded or deleted by an organizational admin.

### 5. Sensitive / Privacy-Bound
- **Examples**: User preferences, PII (if strictly necessary and legally compliant).
- **Policy**: Governed by strict Data Subject Access Request (DSAR) rules. Must be deleted immediately upon user request or account termination, overriding other retention rules.

## Implementation Principle
Retention is evaluated periodically via background chron jobs or lifecycle events (e.g., `ProjectDeleted` event), rather than relying solely on active agent logic.
