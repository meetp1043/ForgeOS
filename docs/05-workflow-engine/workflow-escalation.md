# Workflow Escalation

Escalation moves a problem up the chain of authority when lower levels cannot resolve it.

## Escalation Chain
```
Agent (self-fix attempt)
  → Parent Agent / Engineering Manager
    → Specialist Peer (consulted for domain expertise)
      → Executive Agent (CEO / Product Manager)
        → Human Administrator (final authority)
```

## Escalation Triggers
- Agent retries exhausted.
- Agent explicitly reports it is blocked or uncertain.
- Conflict between two agents that cannot be auto-resolved.
- Budget threshold exceeded.
- Security vulnerability detected.
- Production incident detected.

## Escalation Payload
An escalation event must include:
- **Workflow ID and Step ID**: Exactly where the problem occurred.
- **Error Details**: The full error message and stack trace (if applicable).
- **Agent Context Summary**: What the agent was trying to do.
- **Attempted Resolutions**: What the agent already tried.
- **Recommended Action**: The agent's best guess at a resolution (if any).

## Escalation Limits
A workflow cannot escalate infinitely. If the escalation reaches the human level and the human does not respond within the configured approval timeout, the workflow transitions to `EXPIRED`.
