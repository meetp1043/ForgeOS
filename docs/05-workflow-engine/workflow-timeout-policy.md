# Workflow Timeout Policy

Timeouts prevent workflows and steps from running indefinitely.

## Timeout Levels

| Level | Scope | Default | Description |
| :--- | :--- | :--- | :--- |
| **Step Timeout** | Individual step | 30 minutes | Maximum time for a single step to complete. |
| **Agent Timeout** | Agent execution | 15 minutes | Maximum time for an agent's LLM tool-loop. |
| **Tool Timeout** | Single tool call | 5 minutes | Maximum time for a tool execution in the Sandbox. |
| **Approval Timeout** | Approval gate | 48 hours | Maximum time to wait for a human decision. |
| **Workflow Timeout** | Entire workflow | 7 days | Maximum total duration of a workflow instance. |

## Timeout Behavior
When a timeout fires:
1. The timed-out entity (step, agent, or tool) is forcibly terminated.
2. The `ON_TIMEOUT` transition is evaluated.
3. If no `ON_TIMEOUT` transition is defined, the step transitions to `FAILED`.
4. The failure is classified as `TIMEOUT` and evaluated against the retry policy.
5. If retries are exhausted, the workflow follows standard failure handling (compensation, escalation).

## Configuration
Timeouts are configurable per step in the Workflow Definition and can be overridden per Workflow Template. Organization-wide defaults are set in the ForgeOS configuration.
