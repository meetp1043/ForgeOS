# Cache Invalidation

Stale cache leads to agents making decisions based on outdated information, causing regressions or security breaches. The Context Engine must proactively invalidate its cache.

## Invalidation Triggers

The cache for a specific fragment must be invalidated when:
- **Requirements Change**: The PRD is updated.
- **Architecture Changes**: An ADR is superseded.
- **Code Changes**: A new commit is merged to the active workspace branch.
- **Permissions Change**: An agent role's access rights are modified.
- **Project Changes**: Constraints (like the target tech stack) are updated.
- **Workflow Changes**: The task transitions from "Development" to "Testing".
- **Memory Changes**: The underlying facts in the Memory Engine are corrected.
- **Model Changes**: The underlying LLM is swapped, changing token limits or formatting capabilities.
- **Repository Changes**: Directory structures or build configurations are modified.
