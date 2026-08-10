# Agent Retirement

As ForgeOS evolves, certain agent roles may become obsolete or be merged into other roles (e.g., merging separate `React Agent` and `Node Agent` into a `Fullstack Agent`). 

## Retirement States

1. **DEPRECATED**: The agent is still available to finish active tasks in long-running workflows, but the Agent Selection pipeline will no longer route new tasks to this version.
2. **DISABLED / SUSPENDED**: The agent is immediately halted. Active executions are forcibly cancelled. Usually reserved for security emergencies.
3. **RETIRED**: The agent definition is permanently archived.

## Historical Preservation
When an agent is `RETIRED`, its Agent Definition is not deleted from the database. It is simply marked inactive. 
**Historical execution records remain intact.** 
If a user audits a deployment from three years ago, the system must still be able to pull up the exact prompts and policies of the retired agent that executed the deployment.
