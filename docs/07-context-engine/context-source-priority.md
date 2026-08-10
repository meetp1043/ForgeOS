# Context Source Priority

During the `RANKING` phase, not all information is treated equally. If the Context Package is constrained by token limits, lower-priority sources are truncated or compressed before high-priority sources.

## Priority Hierarchy

Priority is conceptually ordered from highest (must include) to lowest (nice to have). Note that **Priority is not identical to Relevance**. A highly relevant StackOverflow snippet has lower priority than an explicit system security policy.

1. **Explicit System/Security Policy**: Hardcoded instructions that prevent prompt injection, secure credentials, and define absolute boundaries. (Never truncated).
2. **Human-Approved Instructions**: Direct overrides or approvals from a human operator.
3. **Current Approved Requirements**: The business objective (PRD/Acceptance Criteria).
4. **Current Approved Architecture**: Architectural constraints (ADRs).
5. **Current Task**: The immediate, specific goal assigned to the agent.
6. **Current Workflow State**: "Where are we in the pipeline?"
7. **Relevant Code**: The specific files the agent must read or modify.
8. **Relevant Tests**: The tests that validate the relevant code.
9. **Relevant Artifacts**: Associated diagrams, API contracts.
10. **Relevant Project Memory**: Distilled facts from the Memory Engine regarding this specific project.
11. **Historical Context**: Past conversations, previous failed agent runs.
12. **General Information**: Broad documentation, external web searches.

## Truncation Strategy
If the Context Package exceeds the token budget, the engine walks up this list from 12 to 1, compressing or dropping items until the budget is met. Items 1-5 are considered **CRITICAL** and triggering truncation on them may result in task failure rather than unsafe execution.
