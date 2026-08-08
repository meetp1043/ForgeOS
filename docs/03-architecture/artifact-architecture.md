# Artifact Architecture

The Artifact System manages the tangible outputs of the software engineering process.

## Artifact Definition
An artifact is any persistent file or configuration block representing project state:
- Requirements (PRDs)
- Architecture (ADRs, System Designs)
- Source code (Java, TS, etc.)
- Tests (JUnit, Jest)
- Infrastructure (Dockerfiles, Terraform)

## Artifact Metadata
- **Artifact Identity**: A unique UUID tracking the artifact across its lifecycle.
- **Version**: Immutable versions allowing the system to diff changes over time.
- **Ownership**: The specific agent or human that authored the current version.
- **Relationships**: DAG links representing dependencies (e.g., *TestArtifact A* depends on *SourceArtifact B*).
- **Storage**: Small text artifacts (PRDs, code) are stored directly in Git. Large binaries or embedded models may be stored in block storage (S3/MinIO).
- **Provenance**: A cryptographic or logical audit trail linking the artifact back to the exact LLM prompt that generated it.
