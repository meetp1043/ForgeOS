# Provider Configuration

All provider credentials are externalized via environment variables.

- OpenAI: `spring.ai.openai.api-key`
- Ollama: `spring.ai.ollama.base-url`

If a key is missing, Spring Boot will conditionally ignore the bean (`@ConditionalOnProperty`), and the `ModelRouter` will skip that provider. The application will continue to start.
