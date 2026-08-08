# Agent Creation Rules

New agent roles cannot be introduced into the ForgeOS organization arbitrarily. 

## Requirements for a New Agent
To create a new agent (e.g., a "Blockchain Specialist"), the following must be formally defined and committed to the registry:
1. **Role Definition**: A clear title and purpose.
2. **Responsibilities**: What the agent owns.
3. **Permissions**: The specific, minimal set of tool permissions it requires.
4. **Tools**: The JSON schemas for any custom tools it needs.
5. **Evaluation Criteria**: How the system will measure its success.
6. **Prompt**: The thoroughly tested system prompt.
7. **Security Review**: An audit proving the agent's tool access doesn't violate existing boundaries.

## Activation
No agent becomes active in the Orchestrator merely because someone dropped a prompt file in a directory. The agent must be explicitly registered in the database, attached to the taxonomy, and enabled by an Administrator.
