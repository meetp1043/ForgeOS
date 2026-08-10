# Cost Control

AI inference costs are directly proportional to the size of the context window. Feeding a 100k token context to an agent for a task that could have been solved with 5k tokens is a severe operational failure.

## Control Levers

The Context Engine actively manages costs via:

1. **Token Usage Caps**: Enforcing strict `TokenBudgets` per task type (e.g., a simple linting task gets a strict 10k token limit).
2. **Embedding Usage**: Filtering out junk data (logs, raw conversations) before sending them to embedding APIs during Candidate Discovery.
3. **Summarization over Raw Data**: Summarizing large PRDs into bullet points rather than passing the full 20-page document into the prompt.
4. **Retrieval Precision**: Improving search relevance so the top 3 results are sufficient, avoiding the need to inject the top 20 results.
5. **Repeated Context Generation**: Caching Context Packages. If 5 agents are operating on the same branch reviewing the same architecture, the architecture summary should be generated once and cached.
6. **Model Selection**: As outlined in [Model Routing](context-model-routing.md), deferring to cheaper models when the required context is simple.

## Cost Attribution
Every generated Context Package logs an estimated cost based on the token count and target model. This allows project managers to track which agents or workflows are burning the most cash on context retrieval.
