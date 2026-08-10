# Artifact Memory

Artifact Memory forms the bridge between the Memory Engine's internal database and the external, authoritative files that represent the software system.

## Definition
Memory that is strictly tied to, and acts as an index for, an authoritative engineering artifact (e.g., a document, a code file, a log).

## Examples
- Extracting the entity relationship graph from a `schema.sql` file.
- Summarizing the acceptance criteria from a Product Requirements Document (PRD).
- Indexing the vulnerabilities found in a `security_scan_report.pdf`.

## The Authority Principle
A memory should reference the authoritative artifact where possible. If the artifact (e.g., `architecture.md`) is updated, the associated Artifact Memory must be recalculated. The artifact is the source of truth; the memory is merely a searchable, semantic projection of that truth.

## Characteristics
- **Owner**: Project
- **Scope**: Artifact / Project
- **Retention**: Tied strictly to the existence and lifecycle of the underlying artifact.
- **Access**: Inherits the access control of the underlying artifact.
- **Typical Retrieval**: Semantic search for code context, querying requirements during implementation.
