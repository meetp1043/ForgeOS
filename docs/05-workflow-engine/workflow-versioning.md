# Workflow Versioning

Workflow definitions are versioned to ensure reproducibility and safe evolution.

## Versioning Rules
- Workflow definitions use Semantic Versioning (`MAJOR.MINOR.PATCH`).
- **MAJOR**: Incompatible changes to the step graph (e.g., removing a required approval gate).
- **MINOR**: New steps or transitions added without breaking existing flows.
- **PATCH**: Bug fixes in conditions, descriptions, or timeout values.

## Running Instance Binding
- A running workflow instance is permanently bound to the exact definition version it was created with.
- If definition v1.2.0 is updated to v1.3.0 while an instance is running, the running instance continues using v1.2.0.
- New workflow instances will use v1.3.0.

## Migration
- If a critical bug is discovered in a running definition version, a human administrator may force-migrate a running instance to a new version. This is a high-risk operation requiring explicit approval and is logged as a critical audit event.
- Automated migration is not supported. Silent version changes would violate auditability.

## Version History
All published versions of a workflow definition are retained in the database. They are never deleted, ensuring historical executions can always be traced back to their exact definition.
