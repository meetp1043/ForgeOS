# Agent Memory Policy

The Memory Policy defines an agent's authority to interact with the persistent Knowledge Graph (Memory Engine).

## Capabilities

The policy defines whether the agent role can:
- **READ**: Query the Memory Engine for historical context (typically handled via the Context Engine).
- **WRITE/CREATE**: Publish new facts or decisions to the global memory store.
- **MODIFY**: Alter or deprecate existing facts.

## Role-Based Examples

### Solution Architect
- **Can Create**: Architectural decisions, technology selections, module boundaries.
- **Can Modify**: Deprecate old architecture patterns.

### Backend Engineer
- **Can Create**: Implementation details, API nuances, specific bug workarounds.
- **Cannot Modify**: Organization-wide architecture policies. (If a Backend Engineer thinks an architecture is wrong, they must escalate, not silently rewrite the memory).

All writes to the Memory Engine are gated by the Memory Engine's internal authorization policies, ensuring agents cannot bypass rules by directly manipulating the database.
