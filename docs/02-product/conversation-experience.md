# Conversation Experience

The natural language interface allows the user to direct the AI organization intuitively. 

## Supported Interactions

### Commands
Directing the system to take action.
- *"Build me an inventory management system."*
- *"Run the tests again."*
- *"Deploy staging."*
- *"Pause the project."*

### Context Queries
Asking the system about its state or decisions.
- *"Show me the architecture."*
- *"Why did the backend agent choose PostgreSQL?"*
- *"What's blocking the project?"*

### Approvals and Overrides
Interacting with the approval model via chat.
- *"Approve the database migration."*
- *"Reject that PR, tell the frontend agent to use Tailwind instead."*

## Conversational Principles

- **Context Awareness**: The chat must inherently understand which project and workspace the user is referring to.
- **Clarification**: If a command is ambiguous (e.g., *"Make it faster"*), the Executive agent must ask follow-up questions to define measurable acceptance criteria before acting.
- **Summaries**: When a user returns after a period of inactivity, the system should offer a concise summary of what the agents accomplished while they were gone.
- **Interruptions**: Users can interrupt an ongoing agent task via the chat (e.g., *"Stop working on the auth module, we are pivoting to OAuth only"*), and the system must gracefully halt and re-plan.
