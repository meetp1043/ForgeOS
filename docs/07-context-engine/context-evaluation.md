# Context Evaluation

How do we know the Context Engine is doing a good job? Evaluation measures the qualitative impact of the assembled context on agent task success.

## Evaluation Dimensions

1. **Relevance**: Did the context actually pertain to the task?
2. **Completeness**: Was everything necessary included?
3. **Precision**: How much of the included context was actually used/referenced by the agent?
4. **Task Success**: Correlation between Context Package size/composition and whether the agent successfully resolved the ticket.
5. **Wrong-Context Rate**: How often did human reviewers note that the agent acted on superseded or incorrect information?
6. **Missing-Context Rate**: How often did the agent explicitly halt and complain, "I need more information about X"?
7. **Stale-Context Rate**: How often was cached data used when live data was required?
8. **Token Efficiency**: The ratio of useful tokens to total tokens.
9. **Cost**: Financial expense per successful task resolution.
10. **Latency**: Impact of context assembly on Time-to-First-Token (TTFT).

## Evaluation Methodology
Evaluation is performed via **LLM-as-a-Judge** (offline grading of random samples of Context Packages) and **Agent Telemetry** (tracking if the agent actually called tools to read files that were ostensibly already summarized in the prompt, indicating the summary was insufficient).
