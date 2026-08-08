# Agent Retirement Rules

Agent roles are not permanent. As the ForgeOS platform evolves, roles may become obsolete, redundant, or require replacement.

## Retirement Triggers
- **Role Obsolescence**: A technology shift makes the role unnecessary (e.g., a dedicated "jQuery Specialist" agent is no longer needed).
- **Consolidation**: Two overlapping roles are merged into one (e.g., "Cloud Engineer" absorbs "Infrastructure Engineer").
- **Performance**: An agent role consistently fails evaluation metrics and cannot be improved through prompt engineering.

## Retirement Process
1. **Deactivation**: The agent role is marked as `DEPRECATED` in the registry. It will no longer be assigned new tasks by the Orchestrator.
2. **Drain**: Any in-flight tasks assigned to active instances of this role are allowed to complete or are gracefully reassigned.
3. **Migration**: If a replacement role exists, the Orchestrator's routing rules are updated to point to the successor.
4. **Historical Preservation**: The agent's definition (prompt, tools, permissions, version history) is archived in the registry, never deleted. This ensures full auditability of past project executions that used the retired role.
5. **Prompt Versioning**: The retired prompt version remains accessible for historical replay and debugging, but is flagged as non-deployable.

## Constraint
Retirement must be approved by an Administrator. An agent cannot retire itself or another agent.
