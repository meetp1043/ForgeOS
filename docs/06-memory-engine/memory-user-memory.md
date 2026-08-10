# User Memory

User memory personalizes the ForgeOS experience by retaining the preferences, working style, and explicit instructions of individual human users.

## Definition
Information tied directly to a specific user identity, designed to make human-agent collaboration smoother and more efficient without requiring constant restatement of preferences.

## Examples
- "Prefers brief, bulleted summaries over long paragraphs."
- "Primary programming language is TypeScript."
- "Prefers to be notified via email for critical deployment approvals."

## Privacy and Ethics Constraint
ForgeOS must **not** infer, deduce, or store sensitive personal attributes (e.g., political affiliation, medical data, personal life details) from casual conversation. User memory must respect strict privacy controls.

## Characteristics
- **Owner**: User
- **Scope**: User
- **Retention**: Indefinite, but subject to immediate, unconditional deletion upon user request (Right to be Forgotten).
- **Access**: Strictly limited to the agents interacting directly with the user, or executing tasks on behalf of the user.
- **Typical Retrieval**: Formatting agent responses, configuring dashboard UI, routing notifications.
