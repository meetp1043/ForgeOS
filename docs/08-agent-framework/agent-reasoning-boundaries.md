# Reasoning Boundaries

ForgeOS agents often employ Chain-of-Thought (CoT) or internal monologues to break down complex problems. 

## Non-Exposure Principle
**Do not require the storage or exposure of private chain-of-thought for the system to function.**

Internal LLM reasoning is highly volatile, unstructured, and often contains dead-ends or hallucinations that are eventually self-corrected. 

## What ForgeOS Records
ForgeOS relies on the *outputs* and *actions* of the agent, not its internal monologue. The Framework must durably record:
- **Decisions**: Explicitly stated choices (e.g., "I will use ArrayList").
- **Actions**: Executed state changes.
- **Tool Calls**: The exact JSON request and response payloads.
- **Evidence**: The logs proving success.
- **Results**: The final structured output payload.
- **Summaries**: Human-readable explanations generated *after* reasoning is complete.
- **Uncertainty**: Explicit confidence scores.

While the raw LLM trace (including CoT) is captured by [Observability](agent-observability.md) for debugging and LLM evaluation purposes, it is **not** used as semantic memory or parsed for business logic by other components.
