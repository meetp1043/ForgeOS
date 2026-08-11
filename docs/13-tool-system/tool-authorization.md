# Authorization & Risk

The `ToolAuthorizer` is a 15-step gatekeeper.

## Permissions
Tools demand granular permissions (e.g., `FILESYSTEM_WRITE`, `GIT_COMMIT`). These are cross-checked against the `X-Tenant-ID` membership of the actor.

## Risk Evaluation
- `LOW`: (e.g. `fs_read`) Executed via `NoOpSandbox` immediately.
- `MEDIUM`: (e.g. `fs_write`) Executed, but closely audited.
- `HIGH`: Requires Human Approval in PRODUCTION.
- `CRITICAL`: Entirely blocked from execution until the secure Docker/Kata container sandbox is delivered in Phase 19.
