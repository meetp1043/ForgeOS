# Model Gateway & Provider Layer (Phase 24)

The **Model Gateway** is the centralized access point for all AI provider interactions in ForgeOS. No agent, workflow, tool, or service is permitted to invoke an external LLM provider directly. 

## Architectural Principles
1. **Provider Independence**: Core ForgeOS services rely exclusively on `ModelProvider` and never import provider-specific SDKs (like OpenAI or Anthropic clients).
2. **Strict Isolation**: Agents request a `ModelProfile` (e.g., `FAST`, `CODE`, `REASONING`) rather than a specific model. The gateway routes this securely.
3. **Budget and Usage Constraints**: All requests undergo budget checks. If an agent’s cost exceeds the predefined threshold, the request is terminated.
4. **Resiliency & Fallback**: Network requests are unpredictable. The gateway implements an explicit, governed fallback chain. If `OPENAI` rate-limits the platform, it falls back to a cheaper/local model like `OLLAMA` depending on the tenant's data classification policy.

## Error Mapping & Fallback Loops
`ModelError` normalizes all provider-specific exceptions into standard categories (`RATE_LIMIT`, `TIMEOUT`, `AUTHENTICATION_ERROR`, etc.).

The `ModelRouter` calculates a **Provider Chain** based on data classification:
- **Public Data**: OPENAI -> OLLAMA -> MOCK
- **Restricted Data**: OLLAMA (only local inference allowed). If OLLAMA is unavailable, the request fails safely.

The `ModelGatewayImpl` iterates through this chain. If it receives a **Retryable Exception** (like a timeout), it marks `fallbackUsed=true` and attempts the next provider. If it receives a **Non-Retryable Exception** (like invalid API keys or a content policy violation), it aborts the entire chain immediately.

## Data Models
- **`ModelRequest`**: Captures temperature, max tokens, profiles, output schemas, budgets, and security classification.
- **`ModelResponse`**: Returns content, structured output mappings, latency, and detailed `TokenUsage`.
- **`ModelRoutingDecision`**: Audits why a particular model was chosen (latency, cost, data policy).
- **`TokenUsage` & `ModelCost`**: Enforces strict financial controls over generative AI usage.
