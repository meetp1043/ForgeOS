# Workflow Orchestration

The Orchestrator is the runtime component that drives the execution loop. It is the bridge between the Workflow Engine (state machine) and the Agent Runtime (LLM execution).

## Responsibilities
- **Step Scheduling**: Determines which steps are eligible to run next.
- **Agent Dispatching**: Sends `AGENT_TASK` steps to the Agent Runtime for execution.
- **Tool Dispatching**: Sends `TOOL_EXECUTION` steps directly to the Execution Sandbox.
- **Event Processing**: Listens for `StepCompleted`, `StepFailed`, and `ApprovalGranted` events to advance the workflow.
- **Timeout Enforcement**: Monitors running steps and triggers `ON_TIMEOUT` transitions when limits are exceeded.
- **Failure Coordination**: Initiates retry, compensation, or escalation logic based on the step's configured policies.

## Orchestration Patterns
- **Saga Pattern**: Long-running workflows use a saga-style coordination where each step has an optional compensating action. If step N fails, steps N-1, N-2, etc. are compensated in reverse order.
- **Choreography vs. Orchestration**: ForgeOS uses **centralized orchestration** (the Orchestrator explicitly drives the workflow) rather than choreography (agents independently reacting to events). This ensures determinism and auditability.
