# Model Policy

Policies dictate preference when multiple providers satisfy a request.

- `DEFAULT`: Platform default.
- `CHEAP`: Prioritizes `LOW_COST` models.
- `LOCAL_ONLY`: strictly requires Ollama/local inference.
- `PREMIUM`: Prefers high-cost, high-reasoning models.
- `MOCK_ONLY`: Bypasses all network boundaries, used exclusively for tests.
