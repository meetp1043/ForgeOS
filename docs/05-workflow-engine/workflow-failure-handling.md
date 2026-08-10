# Workflow Failure Handling

Failure handling defines how the workflow engine responds to errors at every level.

## Failure Categories

| Category | Description | Typical Recovery |
| :--- | :--- | :--- |
| **Agent Failure** | The agent crashed, exceeded tokens, or produced invalid output. | Retry with fresh agent instance. |
| **Tool Failure** | A tool returned an error (e.g., compiler error, test failure). | Retry (if transient) or escalate to agent for fix. |
| **Model Failure** | The LLM API returned 500, rate-limited (429), or timed out. | Model Router falls back to alternate provider. |
| **Network Failure** | Transient connectivity issue to an external service. | Retry with backoff. |
| **Validation Failure** | Agent output does not match the expected schema or acceptance criteria. | Re-dispatch to agent with error feedback. |
| **Test Failure** | The QA verification step detected a defect. | Reroute to the implementing agent for fix. |
| **Security Failure** | The Security Engineer detected a vulnerability. | Block deployment and escalate. |
| **Permission Failure** | An agent attempted an unauthorized tool invocation. | Fail immediately. No retry. Audit alert. |
| **Budget Failure** | Token or cost budget exceeded. | Pause workflow and escalate to human. |
| **Infrastructure Failure** | The Sandbox container failed to provision. | Retry with backoff. Escalate if persistent. |

## Failure Escalation Chain
```
Step fails → Retry Policy → Compensation → Parent Step → Workflow Escalation → Human
```

## Cascading Failure Prevention
If a step fails and triggers compensation in a parallel container, the engine must prevent a cascade where compensating steps also fail, causing infinite error loops. Compensation failures are logged and immediately escalated to human intervention.
