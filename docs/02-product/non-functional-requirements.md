# Non-Functional Requirements

- **Security**: The system must enforce least privilege for agents. Agents cannot execute arbitrary commands outside their isolated workspace. API keys must be encrypted at rest and in transit.
- **Availability**: The core orchestration engine should target 99.9% uptime.
- **Reliability**: Workflows must be deterministic and recoverable. If an agent crashes mid-task, the orchestrator must retry or resume from the last known good state.
- **Performance**: The UI dashboard must render within 200ms. LLM latency is expected, but the system must provide immediate optimistic UI feedback (e.g., "Agent is thinking...").
- **Scalability**: The system must be able to orchestrate hundreds of concurrent projects across different users without cross-talk or blocking.
- **Maintainability**: The core engine must strictly follow the modular boundaries defined in the architecture, allowing easy replacement of the LLM interaction layer.
- **Observability**: Every agent action, token consumed, and error encountered must be logged with a unique trace ID.
- **Auditability**: All generated code must be traceable back to the specific prompt and agent that generated it.
- **Accessibility**: The web interface must comply with WCAG 2.1 AA standards.
- **Portability**: The ForgeOS engine must be deployable via Docker to any cloud provider or local machine.
- **Cost efficiency**: The system must track and limit token usage, providing users with budget alerts.
- **Privacy**: The system must not leak context from one user's project into another user's prompt.
- **Data isolation**: Workspaces must be strictly separated at the filesystem or container level.
- **Extensibility**: New agents and tools must be pluggable via a defined interface without modifying the core engine.
- **Disaster recovery**: Project memory and state must be backed up to allow recovery in the event of persistent volume loss.
