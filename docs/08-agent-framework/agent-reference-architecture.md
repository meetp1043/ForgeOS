# Agent Reference Architecture

The Agent Framework relies on the following internal Spring Boot architectural components:

## Core Components
- `AgentRegistryService`: Manages CRUD and versioning of definitions.
- `AgentSelectionEngine`: Executes the matching logic against capabilities and permissions.
- `AgentExecutionOrchestrator`: The main state machine controlling the `Agent Instance` lifecycle.
- `PromptBuilder`: Concatenates System, Policy, Context, and Task instructions securely.
- `ToolExecutionSandbox`: The interface to the ephemeral container infrastructure.
- `ToolAuthorizer`: Validates tool requests against the agent's permission matrix.
- `ModelRouterClient`: Sends the finalized payload to the Model Router and handles streaming responses.
- `ResultValidator`: Validates the final JSON output against the `Agent Output Policy`.
- `CostTracker`: Calculates token usage and enforces budgets.
- `AuditLogger`: Writes immutable records of all state changes.
