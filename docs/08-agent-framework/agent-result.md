# Agent Result

The Result is the final state payload emitted by an Agent Instance when it concludes its execution (whether through success, failure, or escalation).

## Result Finality
Once an agent emits a Result, its Instance transitions to `COMPLETED`, `FAILED`, or `ESCALATED`, and the execution context is destroyed. The Result is the only artifact passed back to the Workflow Engine.

## Processing the Result
1. **Validation Layer**: The Agent Framework intercepts the Result and applies the [Validation Policy](agent-validation-policy.md). If the agent claims success but the JSON lacks required fields (like test output), the Framework may automatically reject it and trigger a Retry loop.
2. **Routing**: Valid Results are sent to the Workflow Engine to update the ticket status.
3. **Memory Extraction**: Architectural decisions or facts within the Result are routed to the Memory Engine for global persistence.
4. **Audit**: The entire Result payload is logged.
