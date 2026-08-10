# Agent Planning

Agents are encouraged to generate an explicit plan before taking destructive actions. Planning reduces hallucinations, improves observability, and allows for Manager Agent oversight.

## Plan Structure
A standard agent plan contains:
- **Objective**: Re-statement of the goal in the agent's own words.
- **Steps**: Sequential list of actions to take.
- **Dependencies**: What must exist before a step can start.
- **Expected Outputs**: What success looks like for each step.
- **Validation Criteria**: How the agent will prove the step worked.
- **Risks**: Potential pitfalls.
- **Estimated Cost/Duration**: (Optional, primarily for Architect/Manager roles).

## Authority Boundaries
An agent's plan is strictly bound by its [Authority](agent-authority.md) and [Permissions](agent-permissions.md).

**Crucial Constraint**: An agent cannot create a valid plan that requires permissions it does not possess. 
- *Example*: A Junior Frontend Engineer cannot write a plan that includes "Step 3: Drop the staging database and recreate it," because it lacks the `DATABASE_DELETE` permission. The framework will reject this plan if submitted for formal approval.
