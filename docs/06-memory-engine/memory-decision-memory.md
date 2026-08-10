# Decision Memory

Decision memory is one of the most highly privileged and tightly controlled memory types in ForgeOS. It records the authoritative resolutions to architectural, product, or procedural debates.

## Definition
A structured, immutable (though supersedable) record of an important decision, capturing the context, the rationale, and the chosen path.

## Structure
Each decision memory must explicitly retain:
- **Decision**: The final resolution (e.g., "Use PostgreSQL").
- **Reason**: The technical or business justification.
- **Alternatives**: Options considered and rejected (e.g., "MongoDB, Cassandra").
- **Decision Maker**: The Actor (Human or Principal Agent) who approved it.
- **Date**: Timestamp of approval.
- **Status**: `ACTIVE`, `SUPERSEDED`, or `DEPRECATED`.
- **Superseded Decision**: Pointer to the previous decision (if applicable).
- **Related Artifacts**: Links to the ADR, PR, or PRD document.

## Characteristics
- **Owner**: Project / Principal Architect
- **Scope**: Project
- **Retention**: Indefinite
- **Access**: Broadly accessible for read; strictly controlled for write.
- **Typical Retrieval**: Code generation guardrails, architecture reviews, and preventing agents from reopening settled debates.
