# Audit Storage

Audit events are stored in the `audit_events` table.

## Append-Only Design
The `AuditEventEntity` intentionally does not inherit from `BaseEntity`. It lacks an `updated_at` column because audit records are immutable once written.

## JSONB Payload
The `payload` column is `JSONB`, allowing arbitrary state snapshots (e.g., the previous capabilities of an Agent vs the new capabilities) without requiring rigid schema alterations for every new audit type.

## Secrets
**CRITICAL**: The Audit Engine must strip all sensitive data (passwords, tokens, API keys, PII) before persisting the JSONB payload.
