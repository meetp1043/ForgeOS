# Artifact Storage

ForgeOS agents generate large outputs: source code, binaries, complex test reports, and logs.

## The Reference Pattern
We **do not** store large binary files directly in PostgreSQL rows. Instead, the `artifact_metadata` table stores the relational context (who generated it, for what project, when) and a `storage_ref`.

## Future Storage Provider
The `storage_ref` will eventually point to an object storage system (e.g., AWS S3, MinIO) handled by an `ArtifactService` in future phases.
