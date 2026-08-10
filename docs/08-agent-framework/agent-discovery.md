# Agent Discovery

Agent Discovery is the process by which the Framework locates the optimal agent for a given task. 

## Discovery Anti-Pattern
**Do not select agents by name alone.** 
Hard-coding a workflow to assign a task to `agent: bob-the-coder` breaks when Bob is retired or lacks the permissions for a new technology stack.

## Discovery Vectors

Agents should be discovered dynamically by querying the [Agent Registry](agent-registry.md) across multiple dimensions:

1. **Required Capabilities**: Does the task require `DATABASE_MIGRATION`? Filter for agents that have it.
2. **Technology**: Does the task involve React? Filter for agents tagged with `frontend`, `React`.
3. **Risk Profile**: Is this a `CRITICAL` task? Filter out agents lacking the requisite evaluation status.
4. **Permissions**: Does the task require `DEPLOY_PRODUCTION`? Filter for agents explicitly granted this right.
5. **Role**: The Workflow Engine might specify, "I need someone in the `QA` department."
6. **Cost / Model Compatibility**: Can this agent operate within the $1.00 budget using a local model, or does it demand a frontier model?

By relying on capability-based discovery, ForgeOS can dynamically assemble teams of specialized agents without rigid hard-coding.
