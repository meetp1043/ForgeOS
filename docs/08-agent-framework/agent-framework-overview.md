# Agent Framework Overview

The Agent Framework manages the entire lifecycle of an AI agent's execution from assignment to completion. 

## The Execution Pipeline

Every agent task execution conceptually follows this strict pipeline:

1. **User Objective**: A human triggers a high-level goal.
2. **Workflow**: The Workflow Engine breaks the objective into actionable tasks and queues them.
3. **Agent Selection**: The Agent Framework matches the task requirements against the Agent Registry.
4. **Agent Registry**: The central catalog of all immutable Agent Definitions.
5. **Agent Instance**: A temporary runtime binding of an Agent Definition to a specific Task and Project.
6. **Policy Evaluation**: Verifying the agent has the necessary permissions to execute the task.
7. **Context Request**: The Instance asks the Context Engine for necessary data.
8. **Context Package**: The Context Engine returns a highly filtered, prompt-ready context block.
9. **Memory Access**: Retrieving required historical/semantic data (handled via Context Engine).
10. **Model Selection**: The framework asks the Model Router for the optimal LLM based on task complexity and token size.
11. **Agent Execution**: The LLM is invoked.
12. **Tool Calls**: The agent proposes actions (e.g., executing a test, querying a database).
13. **Result**: The agent outputs a structured response containing its findings and actions.
14. **Validation**: The framework checks if the Result meets the mandatory Output Policy.
15. **Evidence**: The agent provides proof of success (e.g., passing test output).
16. **Memory Update**: The agent writes new architectural or implementation decisions back to the Memory Engine.
17. **Workflow Update**: The task is marked complete or escalated.
18. **Audit**: The entire execution trace is logged immutably.
19. **Observability**: Metrics (latency, cost, tokens) are emitted to Datadog/Grafana.

## System Boundaries
The Agent Framework orchestrates these steps but heavily delegates responsibilities to sibling engines (e.g., it does *not* do vector search itself; it calls the Context Engine).
