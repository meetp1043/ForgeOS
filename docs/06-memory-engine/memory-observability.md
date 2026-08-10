# Memory Observability

To maintain a healthy, performant, and accurate Memory Engine, comprehensive observability is required. We must track how memory is used, how fast it is retrieved, and where it fails.

## Key Metrics to Track

### Operational Metrics
- **Writes/Retrievals per second**: Standard throughput monitoring.
- **Retrieval Latency**: Time taken to complete the entire retrieval pipeline (including vector search and LLM embedding generation).
- **Storage Cost**: Growth rate of the database and vector indexes in megabytes/gigabytes.
- **Embedding Cost**: The financial cost (tokens) spent generating embeddings per day.

### Quality Metrics
- **Hit Rate**: How often does a memory retrieval query return useful results (vs. returning an empty array)?
- **Miss Rate**: How often an agent complains it lacks context that was ostensibly stored.
- **Ranking Quality**: Derived from telemetry (e.g., does the agent utilize the #1 ranked memory, or ignore it in favor of the #4 ranked memory?).

### Lifecycle Metrics
- **Incorrect Retrieval Reports**: Instances where a user or agent flags a retrieved memory as a hallucination or completely irrelevant.
- **Memory Corrections**: The frequency of the [Correction](memory-correction.md) workflow being invoked. High correction rates indicate a failure in the candidate validation phase.
- **Supersession Rate**: How often decisions change.

## Dashboards
These metrics must be aggregated and visualized on the ForgeOS internal health dashboard to allow operators to tune retrieval thresholds, adjust embedding strategies, and optimize costs.
