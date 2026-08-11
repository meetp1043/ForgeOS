# Agent Budget & Limits

No agent executes endlessly. The `AgentSupervisor` terminates an execution if it trips:

- **Token Limits**: `consumedTokens > budgetTokens`.
- **Step Limits**: The ReAct loop exceeds `maxSteps` (default: 50).
- **Time Limits**: The execution duration exceeds the maximum policy threshold.

When a limit is reached, the execution transitions to `ESCALATED` or `FAILED`, alerting the Parent Agent or a Human.
