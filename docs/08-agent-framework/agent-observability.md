# Agent Observability

The Agent Framework emits rich telemetry to allow operators to monitor agent behavior, performance, and cost in real-time.

## Tracked Metrics

The Framework tracks and emits events for:
- **Agent Execution**: Start time, end time, status transitions (`ASSIGNED` -> `COMPLETED`).
- **Workflow & Task**: Which tickets are consuming the most agent time.
- **Model & Provider**: Usage distribution across OpenAI, Anthropic, Ollama.
- **Tool Calls**: Count, latency, and error rates of specific tools (e.g., `git_commit` failed 40 times today).
- **Context Build**: How long the agent waited for the Context Package.
- **Memory Retrieval**: How often agents are successfully finding historical facts.
- **Tokens**: Input and output token counts per execution.
- **Cost**: Real-time dollar spend.
- **Retries**: Frequency of inner-loop tool self-corrections.
- **Approvals & Escalations**: How often agents are blocking on human input.

## Data Masking Constraints
**Do not log sensitive data unnecessarily.**
The observability pipeline must strip or mask passwords, API keys, JWT tokens, and private keys from the raw tool outputs or prompts before sending the telemetry to external systems like Datadog or Grafana. Observability is for operational health, not data warehousing.
