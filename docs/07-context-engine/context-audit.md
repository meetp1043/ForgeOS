# Context Audit

Important context decisions must be auditable. If an agent executes a destructive action (like dropping a database table), the security team must be able to prove *why* the agent thought that was a good idea by examining exactly what information it was fed.

## The Audit Log

For high-risk workflows, the Context Engine writes a permanent audit record of the Context Package.

The record includes:
- **Context Request ID**: Unique tracer.
- **Timestamp**: Exact time of assembly.
- **Agent**: The identity and role of the executing agent.
- **Workflow & Task**: The orchestrator state.
- **Model**: The LLM provider and version targeted.
- **Selected Sources (Provenance)**: A list of every item included (e.g., `[GitCommit: abc1234, MemoryID: 9942, Artifact: ADR-005]`).
- **Rejected Sources**: Notable high-relevance items that were explicitly blocked by Security Policies.
- **Policy Decisions**: The explicit boolean gates passed (e.g., `TenantIsolationCheck: PASS`, `SecretScan: PASS`).

## Sensitive Data Constraint
**Do not log secrets or unnecessarily duplicate sensitive content.**
The audit log records *references* (pointers) to the source data, not the raw text itself. It should state "Included ADR-005," rather than duplicating the entire 5-page text of ADR-005 into the Datadog stream.
