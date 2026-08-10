# Model Awareness

The Context Engine must be model-aware. Different AI models (e.g., GPT-4, Claude 3, local Llama-3) have vastly different characteristics, and the Context Package must be assembled to suit the target model.

## Model Characteristics Impacting Context

- **Context Window**: A 128k token window allows for broader inclusion of source code than an 8k token window.
- **Reasoning Ability**: Stronger models can parse unstructured, dense context better than smaller models. Smaller models require the Context Engine to provide explicit, simplified, and highly summarized context.
- **Tool Support**: If the model lacks native function calling, the Context Engine must inject tool descriptions and schemas as raw text into the system prompt.
- **Multimodal Support**: If the model supports vision, the Context Engine can inject UI mockups or architectural diagrams (images) into the package. If not, these artifacts must be stripped or converted to text summaries.
- **Latency**: Massive context windows slow down time-to-first-token (TTFT). For real-time chat agents, context must be aggressively pruned to keep latency low.
- **Cost**: Filling a 128k context window on an expensive frontier model costs significantly more than on an open-source model.

## Assembly Adaptation
During the `ASSEMBLING` phase, the engine reads the model profile provided by the Model Router and adjusts serialization (e.g., XML tags vs Markdown headers) and token limits accordingly.
