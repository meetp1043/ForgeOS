# Agent Performance Model

Agent performance is a function of latency, token efficiency, and correctness. ForgeOS continuously monitors performance to prevent degradation.

## Optimization Strategies

- **Prompt Caching**: For frequently called agents (e.g., Code Reviewer), static portions of the prompt (the role definition, tool schemas) are cached by the LLM provider to reduce time-to-first-token (TTFT) and cost.
- **Model Downgrading**: If the QA Agent successfully generates tests 99% of the time using GPT-4o, the system may automatically test it against GPT-4o-mini to reduce costs, monitoring for regression.
- **Parallel Execution**: The Orchestrator spins up multiple instances of an agent to execute independent tasks concurrently (e.g., three Backend Engineers working on three separate API endpoints).

## Performance Bottlenecks
- **Context Bloat**: The primary cause of slow agent response is an overly large context window. The Context Engine must aggressively prune unnecessary files.
- **Tool Latency**: Agents waiting for a slow tool (e.g., a 10-minute Docker build) spend their execution time blocked. The Workflow engine suspends these agents and rehydrates them when the tool completes.
