# Agent Risks

The Agent Framework introduces specific architectural risks that must be managed.

## Known Risks

1. **The "Confidently Wrong" Paradox**: LLMs are prone to hallucinating evidence. The Validation Policy and Evidence verification layers must remain strictly deterministic and systemic (not LLM-based) to mitigate this.
2. **Context Window Degradation**: As an agent iterates through 50 tool calls in a single task, the prompt context grows massive, causing the LLM to "forget" its initial System Policy. Mitigation: Aggressive context truncation and state summarization during long executions.
3. **Deadlock by Delegation**: Agent A delegates to Agent B. Agent B asks a question back to Agent A. Both enter `WAITING` state. Mitigation: Workflow Engine timeout sweeps and DAG (Directed Acyclic Graph) enforcement.
4. **Runaway Compute**: A logic bug in the framework causes an agent to loop on `npm install` forever. Mitigation: Hard circuit breakers on cost and wall-clock execution time.
