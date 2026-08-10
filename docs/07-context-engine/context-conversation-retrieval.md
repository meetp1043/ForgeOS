# Conversation Retrieval

ForgeOS agents interact heavily with human users and other agents via chat interfaces. While rich in information, conversations are noisy.

## Retrieval Strategy
**Do not automatically include all conversation history.** Feeding a 200-turn chat log into an agent's prompt wastes tokens and confuses the model with discarded ideas and pleasantries.

The Context Engine should only retrieve:
- **Relevant Recent Conversation**: The immediate preceding turns in the active thread.
- **Explicit User Instructions**: Commands like "Make sure you use the new API wrapper."
- **Important Decisions**: "Okay, let's go with Option B."
- **Unresolved Questions**: Clarifications the agent explicitly asked for in the previous turn.
- **Relevant Previous Discussion**: Highly summarized distillations of past threads (via the Memory Engine) that directly pertain to the current task.

Older conversational turns must be aggressively truncated or summarized.
