# Memory Update Policy

Memory in ForgeOS is not static. Requirements evolve, architectures pivot, and bugs are fixed. The Memory Engine must handle updates gracefully without silently destroying historical context.

## The "No Silent Overwrite" Rule
Important memories—especially Decision, Semantic, and Project memories—must **never** be silently overwritten.

If an agent or user updates a core architectural decision, the system must retain the history of that change.

## Update Mechanics

When a memory entry is updated:
1. **New Version Created**: A new entry is created with an incremented `Version`.
2. **Linkage**: The new entry references the old entry in its `Relationships` array (e.g., `SUPERSEDES: <UUID>`).
3. **State Change**: The old entry's state transitions to `Superseded`.
4. **Audit Logging**: The transition must log:
   - `Reason`: Why the change was made.
   - `Actor`: Who made the change (Agent or Human).
   - `Timestamp`: When it happened.
   - `Approval`: Reference to the human approval gate (if required).

## Updates by Agents vs. Humans
- **Human Updates**: Authorized humans can forcefully update or supersede any memory.
- **Agent Updates**: Agents require sufficient permissions to update high-importance memory. A low-tier agent cannot supersede an Architecture memory without a formal workflow (like an ADR review) and subsequent human approval.

## Context Assembly
When assembling context for an agent, the retrieval pipeline inherently filters out `Superseded` memories unless explicitly asked for historical context (e.g., "Why did we stop using MongoDB?").
