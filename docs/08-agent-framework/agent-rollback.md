# Agent Rollback

If a newly activated Agent Version begins failing in production (e.g., it hallucinates continuously or fails to format its output correctly), the system must roll back safely.

## Rollback Procedure

1. **Detect**: Observability triggers an alert (e.g., "Backend Engineer v2.0 has a 40% FAILED rate").
2. **Disable**: The human operator or automated SRE agent changes `v2.0` status to `SUSPENDED` in the Agent Registry.
3. **Restore**: The previous known-good version (`v1.9`) is transitioned from `DEPRECATED` back to `ACTIVE`.
4. **Preserve Execution History**: Any tasks that failed under `v2.0` remain durably logged as having failed under `v2.0`. 
5. **Re-queue**: The Workflow Engine re-queues the failed tasks, which will now be picked up by the newly restored `v1.9` agent.
6. **Analyze Failure**: The logs for `v2.0` are preserved for debugging.

## Immutability Rule
**No historical execution should be rewritten.**
If an agent deletes a file, rolling back the agent version does not magically undelete the file. The file must be restored via Git, and the Execution log must permanently reflect that the bad agent version executed the deletion.
