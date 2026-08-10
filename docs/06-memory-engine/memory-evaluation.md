# Memory Evaluation

To ensure the Memory Engine is actually helping agents—and not confusing them with irrelevant or contradictory context—the system's performance must be quantitatively evaluated.

## Evaluation Metrics

1. **Retrieval Precision**: Out of the memory entries retrieved for a given prompt, how many were actively useful in solving the task?
2. **Retrieval Recall**: Did the retrieval pipeline fetch the necessary authoritative information that *should* have been included? (Measured during post-mortem analysis of failed tasks).
3. **Relevance**: A qualitative score derived from LLM-as-a-Judge evaluations of the assembled context payload.
4. **Staleness**: The average age of memories being injected into the active prompt. High staleness may indicate broken update/supersession flows.
5. **Conflict Rate**: How often the retrieval pipeline surfaces [Conflicts](memory-conflict-resolution.md) that force an agent to escalate.
6. **Correction Rate**: How often users or peer-agents must invoke the [Correction](memory-correction.md) workflow. A high rate indicates poor Candidate Validation.
7. **Memory Hit Rate**: The percentage of agent executions where injected memory directly influenced the outcome.

## Evaluation Methodology
- **Automated Evals**: Background testing suites that run predefined "queries" against the memory database and verify that the expected ADRs or facts are ranked in the Top 3.
- **Agent Feedback**: Agents are prompted to append a small telemetry payload at the end of their task, grading the usefulness of the memory injected into their context.
