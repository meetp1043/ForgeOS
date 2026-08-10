# Context Sources

The Context Engine aggregates information from a diverse ecosystem of backend systems. Each source provides unique value but carries different latency, sensitivity, and authority profiles.

## Catalog of Sources

### 1. User Input
- **Purpose**: Direct commands, clarifications, or approvals from the human operator.
- **Authority**: High (often overrides automated decisions).
- **Freshness**: Real-time.
- **Sensitivity**: Varies based on user context.

### 2. Project Metadata & Requirements
- **Purpose**: High-level constraints, product specifications, and PRDs.
- **Authority**: High (authoritative for business logic).
- **Retrieval**: Queried from the Project/Artifact System.

### 3. Architecture & ADRs
- **Purpose**: Technical guidelines, boundaries, and system design.
- **Authority**: Supreme (for engineering decisions).
- **Retrieval**: Queried from the Artifact System.

### 4. Tasks & Workflows
- **Purpose**: The immediate objective and orchestrator state.
- **Authority**: High (dictates the agent's current goal).
- **Retrieval**: Queried from the Workflow Engine.

### 5. Source Code & Tests
- **Purpose**: The actual implementation and verification logic.
- **Authority**: Absolute (for the current state of the system).
- **Retrieval**: Queried via Git integration and Repository Analyzers.

### 6. Git History
- **Purpose**: Context on recent changes, PRs, and commit intents.
- **Freshness**: Highly volatile.
- **Retrieval**: Git integration.

### 7. Documentation
- **Purpose**: API usage, developer guides, external library references.
- **Authority**: Medium (often lags behind source code).

### 8. Memory
- **Purpose**: Distilled, cross-task learning and historical decisions.
- **Retrieval**: Queried from the Memory Engine.

### 9. Previous Agent Runs & Agent History
- **Purpose**: Error avoidance and continuity.
- **Authority**: Low (agent inference is not authoritative).

### 10. Tool Results
- **Purpose**: Immediate feedback from the environment (e.g., compiler errors).
- **Freshness**: Real-time.
- **Authority**: High (objective fact about the system state).

### 11. External Integrations, Deployment State, Observability & Incidents
- **Purpose**: Operations, SRE, and integration context (e.g., Jira, AWS, Datadog).
- **Sensitivity**: High (often contains credentials or live customer data).
- **Authority**: External data is untrusted unless explicitly verified.
