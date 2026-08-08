# Cost Architecture

AI generation involves significant variable costs (API tokens). ForgeOS must track and limit these costs natively.

## Cost Tracking
The `billing/cost` module subscribes to the `AgentCompletedTaskEvent`. The event contains the `TokenUsage` metadata (prompt tokens, completion tokens, model name).

## Calculations
The system calculates cost by multiplying the token usage by the specific model's pricing profile. It can answer:
- "What did this project cost?"
- "What did the QA agent cost this week?"

## Controls
- **Hard Budgets**: Tenants can set a maximum monthly spend. If exceeded, the Model Router immediately blocks all outbound API requests.
- **Token Limits per Task**: Prevents an agent stuck in an infinite loop from running up a massive bill.
