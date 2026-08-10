# Workflow Execution Model

The execution model defines the core loop that drives workflow progress.

## Execution Loop

1. **Load State**: Read the current workflow instance state from the database.
2. **Determine Executable Steps**: Identify steps whose preconditions and dependencies are all satisfied and whose status is `READY`.
3. **Validate Permissions**: Confirm that the workflow owner and assigned agents have the necessary permissions.
4. **Load Context**: Invoke the Context Engine to assemble the relevant context for each step's assigned agent.
5. **Assign Agent/Tool**: Select the specific agent instance or tool to execute the step (see `workflow-agent-assignment.md`).
6. **Execute**: Dispatch the step to the Agent Runtime or Tool Executor.
7. **Validate Result**: Parse the structured `TaskResult` and check it against acceptance criteria.
8. **Persist Result**: Commit the step's output, status change, and any artifact references to the database within a transaction.
9. **Emit Events**: Publish domain events (e.g., `StepCompleted`, `StepFailed`) to the internal event bus.
10. **Determine Next Steps**: Evaluate outbound transitions from the completed step to identify newly eligible steps.
11. **Repeat**: Loop back to step 2 until no further steps are eligible (workflow is `COMPLETED`, `FAILED`, or `WAITING`).

## Persistence Guarantees
State is persisted **after** step 8 and **before** step 9. This means that if the server crashes during event emission, the state is already saved and the events can be replayed during recovery.
