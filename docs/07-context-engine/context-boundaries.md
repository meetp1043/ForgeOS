# Context Boundaries

Clear separation of concerns is vital for ForgeOS. The Context Engine interfaces with numerous systems but must not usurp their responsibilities.

## Responsibility Matrix

| System | Primary Responsibility in ForgeOS |
| :--- | :--- |
| **Memory Engine** | Stores, indexes, and retrieves retained knowledge (the persistent "Brain"). |
| **Context Engine** | Selects, filters, ranks, and packages relevant information for a specific prompt. |
| **Agent Runtime** | Executes the agent logic, manages API calls to the LLM, and handles prompt submission. |
| **Workflow Engine** | Controls workflow state, dispatches steps, and manages dependencies between tasks. |
| **Model Router** | Selects the appropriate underlying LLM (e.g., GPT-4, Claude 3, local Ollama) based on cost and capability. |
| **Tool System** | Executes authorized tools (compilers, bash scripts, API calls) on behalf of the agent. |
| **Artifact System** | Stores authoritative engineering outputs (code, PRDs, ADRs, test reports). |
| **Project System** | Manages metadata, team structures, and broad project settings. |
| **Security System** | Defines and evaluates RBAC, ABAC, and data classification policies. |
| **Observability System** | Ingests metrics, traces, and logs for monitoring and alerting. |

## Boundary Enforcement
- **Memory vs. Context**: The Context Engine does not write to the database to "remember" things. It queries the Memory Engine.
- **Workflow vs. Context**: The Context Engine does not decide if a task is complete. It provides the acceptance criteria (from the Workflow Engine) to the agent.
- **Security vs. Context**: The Context Engine enforces security by calling the Security System's policy endpoints; it does not invent its own authorization rules.
