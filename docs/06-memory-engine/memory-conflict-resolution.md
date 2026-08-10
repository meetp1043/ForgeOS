# Memory Conflict Resolution

Because ForgeOS operates asynchronously with many agents, conflicting memories can be generated. 

**Example**:
- Agent A (from a chat a month ago): "The project architecture uses MongoDB."
- Agent B (from a PR approved today): "The project architecture uses PostgreSQL."

ForgeOS must not retrieve both and present them to an executing agent as equally valid, as this leads to paralysis or hallucinations.

## Resolution Mechanics

When the retrieval pipeline detects semantically overlapping but factually conflicting memories, it resolves them using the following hierarchy:

1. **Supersession**: If Memory B explicitly `SUPERSEDES` Memory A, Memory B wins automatically.
2. **Approval (Authority)**: A memory validated by a human user or a Principal Architect agent overrides a memory inferred by a junior agent.
3. **Confidence**: A `HIGH` confidence memory (e.g., parsed from a merged configuration file) overrides a `LOW` confidence memory (e.g., inferred from a casual chat).
4. **Timestamp (Recency)**: If Authority and Confidence are equal, the newer memory wins.

## Handling Unresolvable Conflicts
If two conflicting memories have equal authority, confidence, and highly similar timestamps, the retrieval engine must not guess. It must return **both** to the agent with a `CONFLICT_FLAG`, forcing the agent to escalate the ambiguity to a human or higher-tier manager agent.
