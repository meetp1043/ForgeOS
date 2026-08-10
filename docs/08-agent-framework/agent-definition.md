# Agent Definition

An agent in ForgeOS is **not merely a prompt**. An Agent Definition is a comprehensive, structured data contract that strictly defines an organizational role.

## Structure of an Agent Definition

Every Agent Definition must contain the following fields:

- **Agent ID**: A unique, immutable identifier (e.g., `forge-backend-engineer`).
- **Name**: Human-readable name (e.g., "Backend Engineer").
- **Role**: The organizational title mapping to `/AGENTS.md`.
- **Description**: A summary of the agent's purpose.
- **Organization Layer**: Execution, Management, Architecture, etc.
- **Parent Role**: The role this agent reports to (for escalation).
- **Responsibilities**: High-level domain ownership.
- **Capabilities**: Specific technical verbs (e.g., `CODE_WRITE`, `DATABASE_MIGRATION`).
- **Authority**: What decisions the agent is permitted to make independently.
- **Permissions**: Hard system boundaries (e.g., `GIT_COMMIT`, `DEPLOY_DEV`).
- **Tools**: The specific APIs/CLIs the agent is authorized to invoke.
- **Model Policy**: Constraints on which LLMs this agent may use.
- **Context Policy**: What types of context the agent requires to operate.
- **Memory Policy**: What facts the agent is permitted to persist globally.
- **Instruction Policy**: Base system prompts defining persona and behavior.
- **Output Policy**: The required JSON schema or structured format for the agent's result.
- **Validation Policy**: Criteria required before the agent can claim success.
- **Security Policy**: Risk classification and sandboxing constraints.
- **Risk Level**: `LOW`, `MEDIUM`, `HIGH`, or `CRITICAL`.
- **Version**: Semantic versioning of this definition.
- **Status**: `ACTIVE`, `DEPRECATED`, `RETIRED`.

## Constraint
This is a conceptual definition. Do not create actual SQL tables or Java classes in this specification.
