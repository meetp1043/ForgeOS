# Agent Communication

Agents do not chat in free-form text. Inter-agent communication in ForgeOS is strictly typed and routed through the Workflow Engine or the Memory Engine.

## Message Types

All communication occurs via structured payloads with standard schemas:

- **REQUEST**: A peer-to-peer inquiry for information.
- **RESPONSE**: The reply to a request.
- **DELEGATION**: Assigning a sub-task down the hierarchy.
- **RESULT**: Returning the completed output of a task up the hierarchy.
- **REVIEW_REQUEST**: Asking a peer (e.g., Code Reviewer) to inspect an artifact.
- **REVIEW_RESULT**: The structured feedback (Approve / Request Changes).
- **APPROVAL_REQUEST**: Asking a superior or Human for explicit authorization for a dangerous tool.
- **ESCALATION**: Reporting an unresolvable blocker up the hierarchy.
- **FAILURE**: Notifying dependent agents that a task crashed.
- **STATUS**: Periodic heartbeat updates on long-running tasks.

## Why No Chat?
Allowing uncontrolled, free-form conversational threads between multiple autonomous LLMs leads to rapid context degradation, hallucination loops, and massive token waste. Structured messaging forces agents to distill their thoughts into actionable APIs.
