# Context Refresh

Context is not static for the duration of a long-running task. If an agent executes a tool that modifies the environment (e.g., creating a new file), its current Context Package becomes partially obsolete.

## Rebuild vs Reuse
Some context can be reused across turns; some must always be refreshed.

### Reusable Context (Slow-changing)
- System instructions.
- Agent role definitions.
- Project architecture (ADRs).
- High-level requirements.

### Mandatory Refresh Context (Highly Volatile)
- **Production State**: Active server metrics or error rates.
- **Git State**: The immediate output of `git status` or uncommitted diffs.
- **Deployment Status**: "Is the build still running?"
- **Security State**: Live vulnerability scans.
- **Current Task Status**: Success/failure output of the most recently executed tool.

The Context Engine must dynamically fetch the volatile context on every agent turn, while pulling the slow-changing context from the [Cache](context-cache.md).
