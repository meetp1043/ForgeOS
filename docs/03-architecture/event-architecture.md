# Event Architecture

ForgeOS utilizes an event-driven internal architecture to decouple the Spring Modulith components.

## Core Principles
Modules should not invoke each other's APIs synchronously unless absolutely necessary (e.g., querying data). State mutations should publish Domain Events.

## Key Domain Events
- **Project Lifecycle**: `ProjectCreated`, `ProjectArchived`
- **Requirements**: `RequirementCreated`, `RequirementUpdated`
- **Tasks**: `TaskCreated`, `TaskAssigned`, `TaskCompleted`, `TaskFailed`
- **Agents**: `AgentStarted`, `AgentCompleted`, `AgentFailed`
- **Approvals**: `ApprovalRequested`, `ApprovalGranted`, `ApprovalRejected`
- **Artifacts**: `ArtifactCreated`, `ArtifactUpdated`
- **DevOps**: `TestCompleted`, `DeploymentStarted`, `DeploymentCompleted`, `IncidentCreated`

## Event Ownership
The module that mutates the state owns the event. For example, the `task` module publishes `TaskCompleted`. The `orchestration` module listens to `TaskCompleted` to trigger the next step in the workflow. 

This prevents circular dependencies and allows new modules (like `notification` or `audit`) to plug into the system simply by listening to the event bus.
