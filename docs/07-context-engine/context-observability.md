# Context Observability

To maintain a healthy, performant, and cost-effective Context Engine, comprehensive observability is required. We must track how context is built, how fast it is assembled, and where it fails.

## Key Metrics to Track

The Context Engine emits telemetry for every `ContextRequest`:
- **Context Request Count**: Total volume of requests per minute/hour.
- **Context Build Latency**: Time elapsed from `REQUESTED` to `READY` state.
- **Candidate Count**: How many raw items were discovered before filtering.
- **Selected Items**: The number of items that made it into the final package.
- **Rejected Items**: The number of items dropped (and *why* they were dropped—e.g., Security, Scope, Relevance, Token Limits).
- **Token Count**: Total tokens assembled (System + Context + Reservation).
- **Compression Ratio**: The percentage of token reduction achieved if summarization/truncation was engaged.
- **Cache Hit/Miss Rate**: Efficiency of the caching layer.
- **Retrieval Latency**: Time spent specifically waiting on backend sources (Memory DB, Git).
- **Context Failures**: Rate of fatal errors (e.g., `FAIL CLOSED` events due to missing security constraints).
- **Model Context Usage**: Percentage of the target model's total window utilized.
- **Estimated Cost**: The financial burn rate of the assembly process.

## Dashboards
These metrics are aggregated into the central ForgeOS Grafana/Datadog dashboards, allowing SREs to monitor the financial efficiency and latency of the agent pipeline.
