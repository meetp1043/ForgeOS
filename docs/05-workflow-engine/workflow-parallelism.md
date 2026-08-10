# Workflow Parallelism

Parallelism allows independent tasks to execute concurrently, reducing total workflow duration.

## Fan-Out
A `PARALLEL` container step spawns multiple child steps simultaneously. Each child runs in its own agent instance with its own context.

Example: After Architecture Approval, the following can execute in parallel:
- Frontend Engineer: Implement UI components.
- Backend Engineer: Implement REST APIs.
- Database Engineer: Create schema and migrations.

## Fan-In (Synchronization)
The `PARALLEL` container step completes only when all of its children have reached a terminal state (`COMPLETED`, `FAILED`, or `SKIPPED`).

### Fan-In Policies
- **ALL_SUCCESS**: All children must succeed. If any child fails, the parallel step fails.
- **N_OF_M**: At least N children must succeed (e.g., 2 of 3 services must build).
- **BEST_EFFORT**: The parallel step succeeds as long as at least one child succeeds. Failed children are logged but do not block progress.

## Failure During Parallel Execution
If a child step fails and the fan-in policy is `ALL_SUCCESS`:
1. The failed child's retry policy is evaluated first.
2. If retries are exhausted, the `PARALLEL` container step transitions to `FAILING`.
3. All other running children are sent a cancellation signal (graceful).
4. Compensation logic is triggered for children that already completed.

## Resource Limits
The Orchestrator enforces a maximum concurrency limit per workflow to prevent resource exhaustion (e.g., max 5 concurrent agent instances per project).
