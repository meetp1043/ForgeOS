# Sandbox Boundary

To prevent agents from deleting the entire server, ForgeOS uses the `Sandbox` interface.

In Phase 13, because we only implement `LOW` and `MEDIUM` risk tools that enforce native Java path restrictions, we use `NoOpSandbox`.

In Phase 19, `CRITICAL` risk tools (like raw terminal execution) will trigger a `ContainerSandbox` that spins up an ephemeral Docker container with strictly limited memory and network ingress/egress.
