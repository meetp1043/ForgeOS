# Agent Memory

Agent Memory represents the specific experiences and specialized knowledge acquired by an individual AI agent instance over time.

## Definition
The internal "experience" of a specific agent, utilized to improve its own performance, task planning, and error recovery on future assignments.

## Examples
- "The last time I ran this specific build script, it failed because of a missing environment variable."
- "My success rate for resolving React component bugs is higher when I generate a unit test first."
- "Tool `search_web` frequently times out when querying this specific domain."

## Important Constraint
Agent Memory **must not override** Project Requirements or Organizational Policies. An agent cannot decide to skip a required security check just because its Agent Memory suggests "it's faster this way." Agent memory optimizes *execution*, not *rules*.

## Characteristics
- **Owner**: Agent (or Agent Fleet Manager)
- **Scope**: Agent / Task
- **Retention**: Varies (some memories expire with the task; foundational learnings persist to improve the agent's baseline).
- **Access**: Strictly local to the agent instance, though aggregated metrics may be shared with an Evaluator Agent.
- **Typical Retrieval**: Agent pre-computation phase, tool selection, error recovery loops.
