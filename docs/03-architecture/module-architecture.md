# Module Architecture

ForgeOS Core is built as a Spring Modulith. This document defines the internal modular boundaries.

## Core Modules

### 1. `identity`
- **Responsibility**: User authentication, session management, and RBAC integration.
- **Public Interface**: Auth filters, user info retrieval.

### 2. `project` & `workspace`
- **Responsibility**: Managing the lifecycle of projects and the physical/logical isolation of workspaces.
- **Data Ownership**: Project metadata, Workspace volume mappings.

### 3. `requirements` & `artifact`
- **Responsibility**: Parsing PRDs and managing the storage and versioning of generated files, code, and documentation.
- **Events**: Publishes `ArtifactCreatedEvent`, `ArtifactUpdatedEvent`.

### 4. `planning` & `task`
- **Responsibility**: Maintaining the Sprint backlog, task dependencies, and state transitions (TODO -> IN PROGRESS).
- **Public Interface**: Task assignment, status updates.

### 5. `agent` & `runtime`
- **Responsibility**: Defining agent personas, loading context, and managing the lifecycle of an active agent execution loop.
- **Dependencies**: `model`, `tool`, `context`.

### 6. `orchestration` & `workflow`
- **Responsibility**: The high-level state machine. It observes `Task` states, requests `agent` executions, and handles human `approval` gates.

### 7. `context` & `memory`
- **Responsibility**: Interfacing with the Vector/Document store to retrieve relevant project history and inject it into agent prompts.

### 8. `model`
- **Responsibility**: The provider-agnostic abstraction layer for LLMs (routing to OpenAI, BYOK, Ollama).

### 9. `tool` & `execution`
- **Responsibility**: Registering available agent tools and securely executing them in the `Execution Sandbox`.

### 10. `approval`
- **Responsibility**: Managing risk gates, pausing workflows until human validation is received.

## Dependency Rules
- Modules cannot have circular dependencies.
- `orchestration` can depend on `task` and `agent`.
- `agent` cannot depend on `orchestration`. It emits domain events (e.g., `AgentCompletedTaskEvent`) that `orchestration` listens to.
- `model` and `tool` are foundational and should have minimal inbound domain dependencies.
