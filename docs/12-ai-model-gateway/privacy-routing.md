# Privacy Routing

## Data Classification
- `PUBLIC`: Safe for any provider.
- `INTERNAL`: Standard models.
- `CONFIDENTIAL`: Allowed for enterprise-contracted cloud providers (e.g. Azure OpenAI).
- `RESTRICTED`: PII, secrets, unreleased IP. MUST use local models (Ollama).

The Gateway strictly enforces that a `RESTRICTED` request cannot route to OpenAI.
