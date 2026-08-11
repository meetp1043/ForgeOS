# Git Tools

Safe read-only operations:
- `git_status`: Executes `git status --short` locked firmly into the agent's workspace directory via `ProcessBuilder.directory()`.

Write operations (Commit, Push) are deferred to later phases due to the requirement for specific branch-policy approvals.
