# Model Routing

The Agent Framework does not communicate directly with OpenAI or Anthropic APIs. It delegates inference calls to the **Model Router**.

## Routing Request

When the Agent Instance is ready to execute, it sends a payload to the Model Router containing:
- **Agent Model Policy**: E.g., `NO_CLOUD_AI`, `PREFER_CLAUDE`.
- **Task Complexity**: Extracted from the workflow.
- **Context Size**: Token count of the Context Package.
- **Cost Budget**: Max allowable spend.

## The Routing Decision

The Model Router evaluates these constraints and selects the optimal model.

### Fallback Chain Example:
1. **Primary**: `gpt-4o` (Best reasoning, high cost).
2. **Secondary**: `gpt-4o-mini` (If budget is exhausted).
3. **Fallback**: `ollama/llama-3-70b` (If cloud providers are down, or if the data is flagged as privacy-critical).

The Agent Framework must be resilient to changing model providers. It must parse standardized tool-calling schemas (e.g., OpenAI functions format) and rely on the Model Router to translate those schemas to Anthropic or Gemini formats behind the scenes.
