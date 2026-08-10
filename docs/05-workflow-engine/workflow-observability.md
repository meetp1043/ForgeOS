# Workflow Observability

Observability provides visibility into workflow execution for debugging, monitoring, and capacity planning.

## Metrics

| Metric | Description |
| :--- | :--- |
| `workflow.created.count` | Number of workflows created. |
| `workflow.completed.count` | Number of successfully completed workflows. |
| `workflow.failed.count` | Number of failed workflows. |
| `workflow.duration` | End-to-end duration of completed workflows. |
| `step.duration` | Duration of individual steps. |
| `step.retry.count` | Number of retries per step. |
| `step.queue_time` | Time a step spent in `READY` before being dispatched. |
| `approval.wait_time` | Time spent waiting for human approval. |
| `agent.token_usage` | Tokens consumed per agent execution. |
| `agent.cost` | Monetary cost per agent execution. |
| `workflow.cost.total` | Total cost of a workflow instance. |
| `workflow.active.count` | Number of currently running workflows. |

## Tracing
Each workflow instance generates a distributed trace:
```
Workflow Instance → Step A → Agent Instance → Tool Call → LLM API Call
```
All components share a `trace_id` (OpenTelemetry), allowing engineers to follow the complete chain from user request to tool execution.

## Dashboards
The ForgeOS dashboard should display:
- Active workflow status (running, paused, waiting).
- Step-level progress within each workflow.
- Real-time agent execution logs.
- Cost accumulation over time.
- Approval queue with pending requests.
