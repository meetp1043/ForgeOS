# Model Routing Architecture

ForgeOS employs a provider-independent model layer. We do not hardcode dependencies on a specific API.

## Free / Local AI Strategy
This is a critical architectural requirement. ForgeOS must be usable without requiring expensive cloud AI APIs where practical.
Local AI capabilities fundamentally depend on the host hardware (VRAM, compute).
- **Ollama / Local Open-Source Models**: If available, the Model Router defaults to local models for low-complexity tasks (e.g., formatting, simple QA) to save costs.
- **BYOK (Bring Your Own Key)**: Users can supply their own keys for cloud providers to avoid ForgeOS markups.

## Model Layer Abstractions
- **Model Provider**: The entity hosting the model (OpenAI, Anthropic, Gemini, Ollama).
- **Model**: The specific model version (e.g., GPT-4o, Claude-3.5-Sonnet).
- **Model Capability**: Declarative tags (e.g., `Supports-Vision`, `128k-Context`, `Coding-Specialist`).
- **Model Profile**: User preferences dictating cost vs. quality trade-offs.

## The Router
The Router dynamically intercepts requests from the Agent Runtime and selects the optimal model based on:
1. **Task Complexity**: Routing basic tasks to local/cheap models, and complex reasoning to frontier cloud models.
2. **Context Size**: Ensuring the chosen model can fit the assembled prompt.
3. **Cost/Budget**: Downgrading to cheaper models if a project nears its budget limit.
4. **Latency/Fallback**: If OpenAI returns a 500 error, the router automatically retries the prompt against Anthropic or Google Gemini.
