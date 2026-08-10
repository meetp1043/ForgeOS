# Agent Approval

Approval policies dictate the required authorization gates for specific actions, directly tied to the [Risk Classification](agent-risk-classification.md).

## Authorization Thresholds

- **LOW Risk**: Executes autonomously.
- **MEDIUM Risk**: May require peer AI review. (e.g., A Frontend Agent writes code; the Code Review Agent must approve the PR before merge).
- **HIGH Risk**: Requires explicit approval from an Architect or Engineering Manager role.
- **CRITICAL Risk**: **Requires explicit Human authorization.** No AI agent may unilaterally authorize a CRITICAL action.

## The Approval Payload
When an agent hits a tool requiring approval (e.g., `execute_sql("DROP TABLE users")`), it must submit an Approval Request containing:
- The exact action to be taken.
- The business justification (from its internal planning).
- The predicted blast radius (if calculable).

The execution is `PAUSED`. If the Human/Manager rejects the request, the LLM resumes, receives a `TOOL_ERROR: REJECTED` response, and is expected to formulate an alternative plan or escalate.
