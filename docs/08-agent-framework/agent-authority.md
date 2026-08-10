# Agent Authority

Authority defines the decision-making boundaries of an agent. It explicitly defines what an agent may decide, and what it may *not* decide.

## Authority vs Permissions
- **Permissions** dictate if an agent can execute a system action (e.g., "Can I run `git commit`?").
- **Authority** dictates if an agent is allowed to make a conceptual choice (e.g., "Can I decide to switch from PostgreSQL to MongoDB?").

## Examples of Authority Boundaries

### Backend Engineer Agent
- **May decide**: Implementation details (loop structures, private method extraction), internal class naming conventions, specific library usage (if pre-approved in architecture).
- **May NOT decide**: Changing the database technology, altering an established API contract without frontend approval, overriding an active Architecture Decision Record (ADR).

### Solution Architect Agent
- **May decide**: Proposing new architecture, defining module boundaries, selecting technology stacks.
- **May NOT decide**: Approving its own architecture changes without human/Executive sign-off, overriding business requirements.

### Security Agent
- **May decide**: Blocking unsafe PRs, forcing upgrades of vulnerable dependencies.
- **May NOT decide**: Silencing a critical CVE alert solely to speed up deployment.

### Cost Optimization Agent
- **May decide**: Recommending cheaper infrastructure instances, identifying unused resources.
- **May NOT decide**: Silently weakening security, reliability, or compliance controls just to save money.
