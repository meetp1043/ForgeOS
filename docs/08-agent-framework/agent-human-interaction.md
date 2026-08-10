# Human Interaction

ForgeOS is an AI OS, but humans remain in the loop at the executive and oversight layers. The Agent Framework must support rich human interaction.

## Human Capabilities

Humans operating the ForgeOS dashboard can:
- **Approve / Reject**: Respond to `APPROVAL_REQUEST`s.
- **Pause / Resume**: Manually halt a runaway agent.
- **Cancel**: Kill an execution permanently.
- **Override**: Force an agent to take a specific path.
- **Request Changes**: Reject an agent's `Result` (e.g., during Code Review) and provide free-form text feedback ("This UI looks wrong, make the button blue").

## The Override Audit Rule
**Human overrides must not silently modify historical execution records.**
If a human manually fixes a bug in an agent's code and forces the workflow forward, the audit log must explicitly state: `[Execution 4092: Output modified by Human user 'alice' at 14:00]`. The agent cannot be credited with a clean success if it required manual human intervention to fix its output.
