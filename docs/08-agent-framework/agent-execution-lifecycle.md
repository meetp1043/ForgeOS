# Execution Lifecycle

The lifecycle of an Agent Execution defines the precise sequence of operations from the moment a task is assigned until the final audit log is written.

## The Lifecycle Phases

1. **ASSIGNED**: The Workflow Engine passes the Task to the Agent Framework.
2. **INITIALIZING**: The Agent Framework locates the Agent Version, binds it to the Project, and creates the Agent Instance.
3. **POLICY_VALIDATION**: Pre-flight checks ensure the agent has the basic permissions to even attempt the task.
4. **CONTEXT_REQUEST**: The Instance queries the Context Engine for the required data package.
5. **CONTEXT_READY**: The Context Package is successfully assembled and validated.
6. **MODEL_SELECTION**: The Model Router chooses the specific LLM based on budget, context size, and provider availability.
7. **PLANNING**: The LLM evaluates the context and generates an initial internal plan.
8. **EXECUTING**: The agent begins the autonomous thought/action loop.
9. **TOOL_EXECUTION**: (Repeats) The agent invokes external tools; the Framework sandboxes and returns results.
10. **RESULT_GENERATION**: The agent outputs its final structured payload.
11. **VALIDATION**: The Framework checks the output against the `Validation Policy` (e.g., verifying test evidence).
12. **REPORTING**: The final validated result, cost, and artifacts are returned to the Workflow Engine.
13. **COMPLETED**: The Instance is destroyed and the Execution is marked successful.
