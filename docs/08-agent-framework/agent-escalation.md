# Agent Escalation

Escalation is the reverse of delegation. It occurs when an agent encounters a problem it lacks the authority or capability to solve.

## The Escalation Path
Agents escalate up the organizational hierarchy defined in `/AGENTS.md`:
`Agent -> Parent Role (Manager/Architect) -> Executive Agent -> Human`

## Valid Reasons for Escalation
- **Permission Denial**: The agent needs to modify a database schema but lacks `DATABASE_MIGRATION` rights.
- **Architecture Conflict**: The assigned task requires breaking a rule in an active Architecture Decision Record (ADR).
- **Persistent Failure**: The agent has exhausted its `Retry Policy` (e.g., tests keep failing after 5 attempts).
- **Budget Exhaustion**: The task requires more tokens or compute cost than the agent is authorized to spend.
- **Ambiguity**: The requirements are fundamentally contradictory or underspecified.

## Structured Escalation Payload
An escalation is not a vague "I'm stuck" message. It must include:
- **Problem**: Clear description of the roadblock.
- **Context Reference**: Which specific requirement or code snippet is causing the issue.
- **Attempts**: What the agent already tried.
- **Failure Logs**: Tool errors or test output.
- **Risk**: What happens if this isn't resolved.
- **Recommendation**: The agent's proposed solution.
- **Required Decision**: Exactly what the agent needs the superior to decide (e.g., "Do I have permission to delete this deprecated file?").
