# Ollama (Local AI)

Implemented using `spring-ai-ollama-spring-boot-starter`.

- Local-first architecture allows for `RESTRICTED` data processing.
- Supports `CHAT`.
- Does not crash the application if the Ollama daemon is offline; the provider simply returns a connection exception wrapped in `ProviderUnavailableException` upon execution.
