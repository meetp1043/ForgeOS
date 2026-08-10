# Agent Upgrade

Upgrading an agent is analogous to deploying a new version of a microservice. It must be done carefully to avoid breaking active workflows.

## The Upgrade Pipeline

1. **Draft**: A human or an optimization agent proposes a change to an Agent Definition (e.g., adding a new tool to the Frontend Engineer).
2. **Evaluation**: The new draft version is run against the [Regression Suite](agent-regression-testing.md).
3. **Security Review**: The new version is run against the [Adversarial Suite](agent-adversarial-testing.md).
4. **Approval**: A human administrator reviews the evaluation metrics and approves the upgrade.
5. **Activation**: The Agent Registry marks `v2.0` as `ACTIVE` and `v1.0` as `DEPRECATED`.
6. **Monitoring**: The new version is closely monitored in Datadog for spikes in `TOOL_ERROR`s or latency.

## Routing During Upgrades
**Do not immediately replace production agents without evaluation.**
Active Agent Instances running `v1.0` are not terminated. They continue to run `v1.0` until their current Execution completes. Only newly assigned tasks are routed to `v2.0`.
