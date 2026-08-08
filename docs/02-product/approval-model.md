# Approval Model

To guarantee human control over the AI organization, ForgeOS enforces a strict product-level approval workflow based on action risk.

## Approval Actions
- **Approve**: Proceed with the execution.
- **Reject**: Abort the action; state is reverted.
- **Request Changes**: Send the task back to the agent with specific feedback for revision.
- **Delegate**: Assign the approval authority to another human team member.
- **Pause**: Temporarily halt execution without rejecting.
- **Resume**: Continue a paused execution.
- **Emergency Stop**: Immediately kill all active agent processes for a project.

## Risk Categories

### LOW Risk (No approval typically required)
- Writing documentation.
- Generating tests for existing code.
- Formatting/Linting.
*Agents proceed autonomously (Level 3).*

### MEDIUM Risk (Approval configuration optional)
- Modifying business logic source code.
- Adding non-destructive database columns.
- Updating minor dependencies.
*Agents prepare a PR and await review, though users can set policies to auto-approve passing builds (Level 2).*

### HIGH Risk (Approval strictly required)
- Merging significant architectural changes.
- Modifying authentication or security rules.
- Dropping or modifying existing database schemas.
- Triggering a staging deployment.
*Execution completely halts until a human clicks "Approve".*

### CRITICAL Risk (Multiple approvals or Executive human required)
- Deploying to Production.
- Destroying cloud infrastructure.
- Altering billing or cloud credentials.
*Requires explicit, authenticated confirmation (e.g., re-authentication or typing the project name).*
