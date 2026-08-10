# Quality Metrics

Defining the conceptual metrics used to evaluate the Context Engine's performance.

- **Context Precision**: The percentage of retrieved context items that were strictly necessary to complete the task. (High precision = low noise).
- **Context Recall**: The percentage of *actually required* information that was successfully retrieved and included in the package. (High recall = agent doesn't have to guess or search manually).
- **Context Relevance**: A qualitative LLM-as-a-judge score evaluating semantic alignment with the objective.
- **Context Completeness**: A binary measure per-task: Did the context contain everything needed to succeed? (Yes/No).
- **Context Freshness**: The average age of volatile data (like Git state) included in the prompt.
- **Context Security**: The zero-tolerance metric. Number of times unauthorized data bypassed the filters. Must remain `0`.
- **Context Efficiency**: `(Useful Tokens) / (Total Tokens)`. A measure of how much fluff was included.
- **Context Cost**: Dollar amount spent generating embeddings, querying DBs, and LLM inference for summarization *per request*.
- **Context Build Latency**: `t_ready - t_requested`.
- **Context Failure Rate**: Percentage of requests that resulted in a `FAILED` state (e.g., due to missing critical security policies).
