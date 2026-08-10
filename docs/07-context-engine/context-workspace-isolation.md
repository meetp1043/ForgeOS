# Workspace Isolation

A Workspace is a temporary or localized developer environment, typically tied to a specific Git branch, feature flag, or ephemeral infrastructure state.

## Workspace Boundaries
A single Project may contain many Workspaces (e.g., `feature/login-rework` vs `main`).

When an agent requests context, the Workspace boundary dictates the *version* of the truth it receives.
- If the agent is operating in `Workspace: feature-x`, the Context Engine must fetch the source code, tests, and active artifacts from the `feature-x` branch, not `main`.
- Infrastructure context (e.g., "Is the database up?") must be scoped to the ephemeral database attached to that specific workspace.

## Access Boundaries
Agents generally operate strictly within a single Workspace at a time. Workspace isolation prevents "ghost bugs" where an agent reads code from `main` but attempts to execute tests against `feature-x`.
