# Git Integration

Git provides the temporal state of the repository. Context must be accurately anchored to the correct point in the version control history.

## Relevant Git Context
- **Branch**: The current active working branch (e.g., `feature/payment-gateway`).
- **Commit**: The specific SHA the agent is operating against.
- **Diff**: The uncommitted changes in the working directory, or the diff of the current PR.
- **Changed Files**: A list of files modified recently.
- **PR**: Associated Pull Request comments and reviews.
- **Issues**: Linked issue tickets.
- **Recent Relevant History**: Commit messages for the last few changes to the target files.

## Scope Constraint
**Do not retrieve unrelated repository history.**
An agent fixing a CSS bug does not need the git history of the database migration scripts from two years ago. Git context must be aggressively filtered by time (recent) and space (target files).
