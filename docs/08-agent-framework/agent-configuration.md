# Agent Configuration

Agent Configuration represents the mutable, runtime parameters applied to an Agent Instance. It is distinct from the immutable Agent Definition.

## Configuration Scope
While the Definition dictates *what* the agent is, the Configuration dictates *how* it runs in a specific environment or workflow.

## Runtime Parameters
- **Model Preference**: E.g., prefer `gpt-4o` if available, otherwise `claude-3.5-sonnet`.
- **Token Budget**: Maximum tokens allowed per turn (e.g., `8000`).
- **Cost Budget**: Maximum dollar spend allowed for this specific execution task (e.g., `$5.00`).
- **Timeout**: Maximum wall-clock time allowed for execution (e.g., `300 seconds`).
- **Memory Limits**: Max number of semantic search results to include.
- **Retry Policy**: How many times to retry on transient model/tool failures (e.g., `3`).

## Constraint
**Do not hard-code secrets.**
Agent configurations must never contain API keys, database passwords, or auth tokens. Secrets are managed by the runtime environment and injected just-in-time into the isolated Tool Execution Sandboxes, remaining completely invisible to the Agent Configuration and the LLM context.
