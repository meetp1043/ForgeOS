# Agent Glossary

- **Agent Definition**: The immutable, abstract configuration of a role (capabilities, permissions, prompt).
- **Agent Instance**: A runtime object binding a Definition to a specific task and context.
- **Capability**: An atomic skill (e.g., `CODE_WRITE`).
- **Permission**: A hard system authorization (e.g., `GIT_COMMIT`).
- **Tool**: An external interface (e.g., `execute_sql`).
- **Sandbox**: The isolated environment where tools run.
- **Execution ID**: The unique tracker for a single task attempt.
- **Result**: The final structured JSON output of an execution.
- **Context Policy**: Rules dictating what data the agent needs.
- **Output Policy**: Rules dictating what data the agent must return.
- **Validation Policy**: Rules dictating the evidence required for success.
- **Delegation**: Assigning work to a subordinate agent.
- **Escalation**: Passing a blocker to a superior agent.
- **Model Router**: The subsystem that selects the actual LLM (OpenAI, Anthropic, etc.).
