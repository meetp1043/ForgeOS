# Observability Architecture

Observability is a P0 architectural concern due to the non-deterministic nature of LLMs.

## Pillars of Observability
- **Structured Logging**: All logs are written in JSON format containing standard fields: `tenant_id`, `project_id`, `task_id`, `agent_id`, `trace_id`.
- **Metrics**: Spring Boot Actuator exports metrics to Prometheus. We track API latency, active tasks, memory usage, and token consumption rates.
- **Tracing**: Distributed tracing (OpenTelemetry) traces a user request from the API boundary, through the orchestration layer, out to the LLM provider, and back.

## Agent-Specific Observability
Standard application logging is insufficient for AI. We specifically capture:
- **Agent Execution Traces**: A full dump of the exact prompt sent and the exact response received.
- **Workflow Traces**: Visualizable DAG states showing exactly when and why a workflow transitioned to a `FAILED` or `BLOCKED` state.
- **Tool Execution Logs**: Capture of `stdout`/`stderr` from the Sandbox to audit what commands an agent actually ran.
