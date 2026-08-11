# Fallback and Resilience

The `ModelRouter` is designed to degrade gracefully.

If `OPENAI` is requested but offline (or its rate limit is hit, resulting in a 429), the Router catches `ProviderUnavailableException`.

## Fallback Rules
1. Never fallback to a provider that violates `ModelPrivacyClassification`.
2. Fallback to `OLLAMA` is always safe for data, assuming local compute is sufficient.
3. If no fallback is available, it bubbles a `ModelGatewayException`.
