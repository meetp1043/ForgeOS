# Model Routing Integration

The Context Engine does not operate in isolation; it works in tandem with the **Model Router**. 

## Bidirectional Communication

1. **Context dictates the Model**: 
   - *Example*: The Context Engine determines that the minimum `CRITICAL` context (source code and dependencies) for a complex refactoring task is 80,000 tokens. It signals the Model Router that a model with >100k context window is required.
   - *Example*: The Context Engine identifies that the target code is highly proprietary and flagged for `NO_CLOUD_AI`. It signals the Router to select a local (BYOK/Ollama) model.

2. **The Model dictates the Context**:
   - *Example*: The Model Router determines (based on cost budgets) that a specific task must use a smaller, cheaper model with an 8k context window. It signals the Context Engine to strictly cap the Context Package at 6,000 tokens (leaving 2k for output).

## Routing Scenarios
- **Small Task**: Minimal context → Route to smaller/cheaper model (e.g., GPT-3.5 or Haiku).
- **Complex Architecture**: Dense ADRs and cross-module context → Route to stronger reasoning model (e.g., GPT-4 or Opus).
- **Large Context**: Massive codebase queries → Route to long-context model (e.g., Gemini 1.5 Pro).

**Do not hard-code a specific provider.** The Context Engine must remain agnostic, dealing in abstract capabilities (token limits, modalities) rather than brand names.
