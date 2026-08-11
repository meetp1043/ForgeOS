# AI Model Gateway Architecture (Phase 12)

The AI Model Gateway is the single internal abstraction through which ForgeOS requests AI capabilities. It isolates business modules (Agent Runtime, Workflow Engine) from specific providers like OpenAI, Anthropic, or Ollama.

## Architecture Diagram

```mermaid
flowchart TD
    App[Agent / Application] -->|ModelRequest| GW(Model Gateway)
    GW -->|Selects Provider| MR(Model Router)
    
    MR -->|Policy & Constraints| PE{Policy Engine}
    MR -->|Available Models| PR[(Provider Registry)]
    
    PR --> Adapter1[OpenAI Provider]
    PR --> Adapter2[Gemini Provider]
    PR --> Adapter3[Anthropic Provider]
    PR --> Adapter4[Ollama Provider]
    PR --> Adapter5[Mock Provider]
```

## Directory Structure
- `provider-abstraction.md`: Interface boundaries.
- `model-router.md`: Policy selection logic.
- `openai.md` / `ollama.md`: Supported providers.
- `privacy-routing.md`: Security and data classification.
