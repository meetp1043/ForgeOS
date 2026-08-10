# Agent Model Policy

The Model Policy defines which underlying Large Language Models (LLMs) an agent is permitted to use, and under what conditions.

## Provider Independence
Agent Definitions must remain independent of specific AI providers (e.g., OpenAI, Anthropic, Gemini, Ollama). The framework relies on abstract model capabilities rather than hard-coded vendor names.

## Policy Factors
Model selection is determined at runtime based on the agent's policy intersecting with:
- **Task Complexity**: A simple code linting task may route to a smaller, faster model.
- **Context Size**: Tasks requiring 100k tokens of context will route to long-context models.
- **Privacy Requirements**: If the task involves PII or proprietary IP tagged `NO_CLOUD_AI`, the policy forces routing to an on-premise/local model (e.g., Ollama/Llama-3).
- **Cost Budget**: If the workflow has exhausted its budget, the agent may be downgraded to a cheaper model.
- **Reasoning Requirements**: Architectural tasks route to "frontier" class reasoning models.

## Local/Free AI Support
ForgeOS explicitly supports local inference via BYOK (Bring Your Own Key) or local engines like Ollama. 
The Model Policy defines fallback strategies:
`Primary (Cloud Frontier) -> Secondary (Cloud Fast) -> Fallback (Local Ollama)`

If a local model is insufficient in context size or capability to fulfill the assigned role, the agent must fail gracefully or escalate to a human, rather than hallucinating bad output.
