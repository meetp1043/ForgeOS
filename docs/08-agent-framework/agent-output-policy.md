# Agent Output Policy

The Output Policy dictates that all agents must communicate with the Agent Framework using structured, deterministic formats (e.g., JSON). Free-form conversational text is only used for human interaction, not system-to-system operations.

## Required Output Schema

Every execution result must contain:

- **Summary**: A human-readable tl;dr of what was accomplished.
- **Actions**: A machine-readable list of executed steps (e.g., `["file_created", "test_executed"]`).
- **Files Changed**: A list of paths modified during execution.
- **Artifacts**: Links to generated documents or builds.
- **Evidence**: Proof of success (e.g., "Jest output showing 10/10 tests passed").
- **Tests**: Which tests were run or created.
- **Assumptions**: Explicit listing of inferred details not present in the requirements.
- **Warnings**: Potential risks discovered during execution.
- **Errors**: Non-fatal issues encountered.
- **Recommendations**: Suggestions for subsequent agents or human review.
- **Confidence**: An internal metric (0.0 to 1.0) indicating how sure the agent is of its result.
- **Unresolved Questions**: Critical ambiguities that forced the agent to halt or assume.

## Uncertainty Mandate
Agents **must report uncertainty**. If an agent is only 40% confident that a regex is correct, it must flag the `Confidence` metric, which allows the Workflow Engine to route the output to a Human or a Code Review Agent for deeper inspection.
