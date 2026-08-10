# Artifact Isolation

Artifacts (documents, ADRs, test reports) within ForgeOS are not universally accessible. They carry sensitivity classifications that the Context Engine must respect during retrieval.

## Sensitivity Classifications

1. **PUBLIC**: Readable by any agent or user within the Project. (e.g., standard READMEs, open-source library docs).
2. **INTERNAL**: Readable by assigned engineering agents within the Tenant. (e.g., proprietary API contracts).
3. **CONFIDENTIAL**: Readable only by explicitly authorized agents (e.g., Lead Architects, Security Agents). Might contain unreleased product features or sensitive business logic.
4. **RESTRICTED**: Readable only by specific human users or highly privileged audit agents. (e.g., HR data, legal compliance reviews).
5. **CRITICAL**: Requires active human presence/approval to even load into context (e.g., production root keys, which should ideally never be in normal context anyway).

## Context Retrieval Enforcement
When a semantic search discovers a highly relevant candidate artifact, the Context Engine checks the artifact's classification against the `ContextRequest`'s permitted clearance level. If the artifact is `CONFIDENTIAL` but the agent is a standard `JUNIOR_CODER` agent, the artifact is silently dropped from the candidate list.
