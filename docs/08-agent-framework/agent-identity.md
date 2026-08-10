# Agent Identity

Agent Identity distinguishes between the abstract definition of a role and a specific historical execution. These concepts must never be conflated.

## Identity Hierarchy

1. **Agent Definition ID**
   - *What it is*: The abstract, unversioned concept of the agent.
   - *Example*: `backend-engineer`
   - *Scope*: Global.

2. **Agent Version ID**
   - *What it is*: A specific, immutable snapshot of the Agent Definition.
   - *Example*: `backend-engineer_v3.2`
   - *Scope*: Global, Immutable. (If we change the backend engineer's prompt, it becomes `v3.3`).

3. **Agent Instance ID**
   - *What it is*: A temporary runtime object binding the Agent Version to a specific project, user, or workflow.
   - *Example*: `backend-engineer_project-x_session-991`
   - *Scope*: Session/Project bounded. Contains stateful memory for the duration of a workflow.

4. **Agent Execution ID**
   - *What it is*: A single, atomic attempt to complete a task.
   - *Example*: `exec_8f92a_attempt_2`
   - *Scope*: Single task. If an agent fails and retries, a new Execution ID is generated.

## Traceability
Every action performed in ForgeOS (e.g., a git commit, a database update) must be traceable down to the `Agent Execution ID`.

*Example Log*: `User 'forge-bot' executed DROP TABLE users. Trace: Execution [exec_1092] -> Instance [dba_session_44] -> Version [dba_v1.1] -> Agent [database-engineer].`
