# Logging Strategy

Structured logging (JSON format in production) is required for observability.

## Conventions
- **Root Level**: `INFO`
- **Application Level**: `DEBUG` (in dev), `INFO` (in prod).
- **MDC (Mapped Diagnostic Context)**: Every request must be tagged with a `traceId`, `tenantId`, and `userId` where applicable.

## Security Rule
Never log:
- Plaintext passwords.
- JWT Bearer tokens.
- AWS/GCP API Keys.
- OpenAI/Anthropic API Keys.
- PII (Personally Identifiable Information) unless explicitly required and masked.
