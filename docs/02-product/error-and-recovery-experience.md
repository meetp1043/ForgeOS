# Error and Recovery Experience

Software engineering involves failure. ForgeOS must handle failures gracefully and provide clear recovery paths.

## Failure Scenarios

- **Agent Fails**: An agent enters an infinite loop or repeatedly fails to generate syntactically correct code.
  - *Recovery*: The Orchestrator halts the agent after a retry limit and flags the task as BLOCKED, notifying the user to provide manual intervention or rewrite the prompt.
- **Tool Fails**: A tool (e.g., `run_command`) returns a system error.
  - *Recovery*: The error output is fed back to the agent. The agent is prompted to analyze the error and try a different approach.
- **Build/Tests Fail**: The generated code does not compile or fails QA.
  - *Recovery*: The QA agent loops the failure back to the Developer agent for a fix. If it fails 3 times, it escalates to the user.
- **Deployment Fails**: Cloud provider rejects the manifest.
  - *Recovery*: DevOps agent attempts to patch. If unsuccessful, SRE agent triggers a rollback to the last stable state.
- **Model / API Fails**: The LLM provider (e.g., OpenAI) returns a 500 or rate limit.
  - *Recovery*: The Model Router automatically fails over to a secondary provider or pauses execution with exponential backoff.
- **Context is Missing**: An agent complains it doesn't know how a specific module works.
  - *Recovery*: The Context Engine performs a wider semantic search. If still missing, it asks the user for the documentation.
- **Requirements Conflict**: The user asks for a feature that breaks an existing architectural rule.
  - *Recovery*: The Architect agent halts planning and explicitly asks the user to resolve the contradiction before writing any code.

## Core Principle
ForgeOS must explain failures clearly in plain English, avoiding cryptic internal tracebacks unless explicitly requested by a developer persona.
