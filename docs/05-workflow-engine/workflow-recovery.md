# Workflow Recovery

The workflow engine must survive infrastructure failures without losing state.

## Failure Scenarios

| Scenario | Recovery Strategy |
| :--- | :--- |
| **Application Restart** | The engine rehydrates all `RUNNING`, `WAITING`, and `PAUSED` workflows from the database on startup. |
| **Machine Restart** | Same as application restart. The database (PostgreSQL) persists independently. |
| **Network Partition** | In-flight LLM API calls may timeout. The step's retry policy handles re-dispatch. |
| **Agent Crash** | The agent instance is lost. The Orchestrator detects the missing heartbeat and reassigns the step to a new agent instance. |
| **Tool Crash** | The Sandbox container exits unexpectedly. The tool execution is marked as `FAILED` and the step's retry policy applies. |
| **Model Outage** | The Model Router automatically fails over to an alternate LLM provider. |
| **Database Crash** | The database is restored from backup. Workflows resume from the last committed checkpoint. RPO applies. |

## Recovery Process
1. On startup, the engine queries for all workflow instances not in a terminal state.
2. Each instance transitions to `RECOVERING`.
3. The engine replays the event log from the last checkpoint to reconstruct the in-memory state.
4. Steps that were `RUNNING` at crash time are evaluated:
   - If the step was idempotent, it is re-dispatched.
   - If the step was non-idempotent (e.g., a deployment), it is marked `FAILED` and escalated for human review.
5. Once recovery is complete, the instance transitions back to `RUNNING`.

## Heartbeat Mechanism
Active agent instances send periodic heartbeats to the Orchestrator. If a heartbeat is missed beyond a configured threshold, the agent is presumed dead and its step is reassigned.
