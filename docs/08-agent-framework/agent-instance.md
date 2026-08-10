# Agent Instance

An Agent Instance is a temporary, stateful runtime object. It is created when an [Agent Definition](agent-definition.md) is bound to a specific workflow execution.

## Instance Composition

The Instance holds references to both static policy and highly volatile runtime state:

- **Agent Definition**: The abstract role (e.g., Backend Engineer).
- **Agent Version**: The exact immutable version being run.
- **Project**: The specific codebase/tenant the agent is operating within.
- **Workspace**: The ephemeral environment (e.g., `feature/branch-x`).
- **Task**: The immediate Jira/Linear ticket.
- **Workflow**: The parent process orchestrating the task.
- **Context**: The loaded `Context Package` from the Context Engine.
- **Permissions**: The actual JWTs or execution tokens granted for this session.
- **Execution ID**: The unique tracer for this attempt.
- **Status**: The current state (e.g., `RUNNING`, `WAITING_FOR_APPROVAL`).

## Ephemerality
An Instance lives only as long as the Execution. If the agent crashes or finishes the task, the Instance is destroyed. Any permanent knowledge gained must be written to the **Memory Engine** or committed to **Git** before destruction.
