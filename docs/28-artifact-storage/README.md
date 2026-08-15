# Artifact & Object Storage Platform (Phase 28)

## Overview
ForgeOS centralizes file persistence across all subsystems (Context Engine, Sandbox, Core Platform) through the **Artifact Platform**. This platform enforces rigid tenant isolation, ensuring a unified security model for file uploads, downloads, and checksum validations.

## Core Abstractions
- **`ArtifactEntity`**: Metadata tracking the artifact's logical identity in Postgres. Fields include `tenantId`, `checksum`, `storageKey`, `artifactType`, and lifecycle `status` (e.g. `AVAILABLE`, `QUARANTINED`, `FAILED`).
- **`ObjectStorageProvider`**: Interface allowing ForgeOS to detach from rigid vendor implementations. We currently ship `LocalObjectStorageProvider` out of the box (for local dev mapping to `/tmp/forgeos-artifacts`), allowing later extensions like AWS S3 or MinIO.

## Security Controls
1. **Tenant Isolation**: By querying `ArtifactRepository` specifically using `findByIdAndTenantId(artifactId, TenantContextHolder.getTenantId())`, ForgeOS intrinsically protects against IDOR (Insecure Direct Object Reference) vulnerabilities. Tenant A cannot fetch Tenant B's artifacts.
2. **Path Traversal Protection**: The local storage implementation explicitly strips `..` and relative paths when writing object keys, ensuring files cannot escape the designated root storage directory.
3. **Checksums**: Each successful upload records a cryptographic SHA-256 hash. If an artifact becomes corrupted, this allows for immediate discrepancy detection.

## Artifact Controller (API)
The platform exposes REST APIs accepting `multipart/form-data`.
- `POST /api/v1/artifacts`: Streams data directly to storage, maintaining bounded memory.
- `GET /api/v1/artifacts/{id}/download`: Leverages Spring's `InputStreamResource` for memory-efficient streaming of large outputs back to the client.

## Hard vs Soft Deletion
Deleting an artifact via `DELETE /api/v1/artifacts/{id}` operates defensively.
1. The `ArtifactEntity` status flips to `DELETED`.
2. ForgeOS makes a best-effort async attempt to call `deleteObject(key)` on the storage provider.
3. If storage fails, metadata reflects deletion, and a background cleanup worker eventually sweeps the disconnected storage key.
