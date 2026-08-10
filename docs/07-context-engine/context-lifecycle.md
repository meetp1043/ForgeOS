# Context Lifecycle

The assembly of a Context Package follows a strict state machine. This lifecycle ensures all filters, rankings, and validations are completed before an agent interacts with the data.

## Lifecycle States

1. **REQUESTED**: The Agent Runtime or Workflow Engine submits a Context Request.
2. **DISCOVERING**: The engine queries backend systems (Memory, Git, Artifacts) for candidate information.
3. **FILTERING**: Candidates are aggressively pruned based on Tenant, Project, Security, and Permission boundaries.
4. **RANKING**: The remaining safe candidates are scored for relevance, authority, and freshness.
5. **ASSEMBLING**: Top-ranked candidates are formatted, summarized (if needed to fit token budgets), and structurally separated into the Context Package.
6. **VALIDATING**: A final pass confirms that no mandatory constraints (e.g., critical security directives) were accidentally truncated.
7. **READY**: The Context Package is complete and cached.
8. **DELIVERED**: The package is injected into the Agent Runtime.
9. **REFRESHING**: An active agent requests an updated state (e.g., pulling new Git changes mid-task).
10. **INVALIDATED**: Underlying source data changed dramatically, rendering the cached package unsafe to reuse.
11. **FAILED**: The pipeline encountered a fatal error (e.g., missing critical security context) and halted.

## Invalidation Rules
A Context Package becomes invalid when:
- The agent switches tasks.
- The underlying codebase undergoes a significant state change (e.g., a branch merge).
- A human explicitly revokes a permission that was utilized to build the package.
- A critical architecture decision (ADR) relevant to the task is superseded during execution.
