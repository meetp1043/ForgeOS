# Agent Result Model

When an agent finishes a task, it must return a structured result to the Orchestrator, rather than a conversational summary.

## The Result Object
The formal `TaskResult` contains:
1. **Status**: `SUCCESS`, `FAILURE`, `ESCALATED`.
2. **Artifacts Modified**: A list of paths to files the agent created or changed.
3. **Summary**: A concise, non-conversational summary of what was done.
4. **Verification**: Proof that the acceptance criteria were met (e.g., "Tests passed: output log attached").
5. **Open Items**: Any technical debt incurred or follow-up tasks required.

## Why Structured Results?
If an agent replies with "I fixed the bug!", the Orchestrator cannot easily parse what files changed to trigger the next workflow step (e.g., QA testing). Structured results allow the state machine to route the output programmatically.
