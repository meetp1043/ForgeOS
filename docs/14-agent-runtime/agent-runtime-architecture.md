# Agent Execution Loop

The `AgentExecutor` runs the core ReAct (Reason, Act, Observe) loop.

1. **Reason**: The agent evaluates its objective, history, and context, then requests a completion from the Model Gateway.
2. **Decide**: The `DecisionEngine` interprets the unstructured model output into an `AgentDecision` (Tool Call, Delegate, Escalation, or Final Result).
3. **Act**: The executor calls the Tool System, waits for the artifact, and observes the result.
4. **Limits**: The `AgentSupervisor` evaluates step limits, token budgets, and timeout criteria before restarting the loop.
