# Agent Failure Model

Agents will fail. The organization must be resilient to these failures.

## Failure Types

- **Tool Failure**: The compiler threw an error, or a bash script exited with code 1.
- **Model Failure**: The LLM API returned a 500, timed out, or hit a rate limit.
- **Context Failure**: The agent generated an invalid JSON response or exceeded the token limit.
- **Requirement Failure**: The agent built a feature, but it fails the PM's acceptance criteria.
- **Code/Test Failure**: The QA agent proves the code is broken.
- **Security Failure**: The Security agent detects a vulnerability.
- **Timeout**: The agent took too long to complete the task.
- **Budget Failure**: The project ran out of API tokens.
- **Permission Failure**: The agent tried to use a tool it lacked permission for.

## Recovery Strategies
1. **Retry (Level 1)**: For transient model failures or simple syntax tool failures, the agent retries automatically (max 3 times).
2. **Reassignment (Level 2)**: The Orchestrator assigns a fresh agent instance with a clear context to attempt the task again.
3. **Fallback**: The Model Router swaps to a different LLM provider.
4. **Escalation / Termination**: The workflow halts and requests human intervention.
