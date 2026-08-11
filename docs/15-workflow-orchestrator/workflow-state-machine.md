# Workflow State Machine

Workflows have an explicit lifecycle separated into Definition and Execution.

## Workflow Definition States
`DRAFT` → `VALIDATING` → `ACTIVE` → `DEPRECATED` → `RETIRED`

## Workflow Execution States
`CREATED` → `QUEUED` → `RUNNING` → `WAITING_APPROVAL` → `COMPLETED` / `FAILED`

## Task Execution States
`PENDING` (Waiting for dependencies) → `READY` → `QUEUED` → `RUNNING` → `COMPLETED` / `FAILED`

Transitions must be explicitly handled by the `WorkflowCoordinator` and `TaskWorker`.
