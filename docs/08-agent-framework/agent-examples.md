# Agent Examples

## Example 1: The Code Review Agent Execution

1. **Trigger**: A Backend Agent opens a PR.
2. **Context**: Context Engine pulls the PR diff, the coding standards, and recent security CVEs.
3. **Execution**: The Code Review Agent analyzes the diff. It spots an unparameterized SQL query.
4. **Tool Call**: It uses `github_add_comment` to flag the line.
5. **Result**: It outputs a `Result` payload marking the PR as `CHANGES_REQUESTED`.

## Example 2: The SRE Agent Handling an Incident

1. **Trigger**: Datadog webhook fires for High CPU on the Payment Service.
2. **Context**: Context Engine pulls the runbook for "High CPU Payment Service" and recent deployment logs.
3. **Execution**: SRE Agent reads the runbook.
4. **Tool Call**: Runs `kubectl top pods` (sandbox captures output).
5. **Analysis**: Notes the pod is OOM-looping.
6. **Tool Call**: Prepares a `kubectl scale deployment` command.
7. **Approval**: Because modifying production is `CRITICAL` risk, the agent pauses and triggers an `APPROVAL_REQUEST` to the Human on-call.
8. **Resume**: Human approves. Agent executes the scale command.
9. **Result**: Agent updates the incident ticket with the resolution and marks `SUCCESS`.
