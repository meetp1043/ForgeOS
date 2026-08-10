# Agent Creation

Creating a new Agent Definition involves more than writing a clever prompt. It is a formal organizational change.

## Creation Process

1. **Role Proposal**: Define the title and reporting structure in the AI Organization hierarchy.
2. **Responsibility Definition**: Document exactly what this agent owns.
3. **Capability Definition**: Select the atomic skills (e.g., `CODE_WRITE`) required.
4. **Authority Definition**: Define the decision-making boundaries.
5. **Permission Definition**: Select the hard system permissions (e.g., `GIT_COMMIT`). (Enforce Least Privilege).
6. **Tool Definition**: Select the JSON schemas for the required tools.
7. **Model Policy**: Define which LLMs are permitted.
8. **Context Policy**: Define what data the agent needs from the Context Engine.
9. **Memory Policy**: Define what the agent can persist.
10. **Output Contract**: Define the specific JSON schema for the agent's Result.
11. **Validation Criteria**: Define the mandatory evidence required for success.
12. **Security Review**: Human security team reviews the permissions and tools.
13. **Evaluation**: The agent runs in a sandbox against test tasks.
14. **Approval**: The CTO/Executive layer approves the creation.
15. **Registration**: The definition is committed to the Agent Registry.
16. **Activation**: The status is set to `ACTIVE`.

## Rule
**An agent prompt file alone does not create an active agent.** Without the accompanying permissions, policies, and registry entry, the Framework cannot execute the prompt.
