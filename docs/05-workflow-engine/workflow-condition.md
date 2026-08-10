# Workflow Conditions

Conditions are boolean expressions evaluated during transitions to determine the execution path.

## Condition Types

- **Step Result Condition**: Based on the output of a previous step (e.g., `steps.build.exit_code == 0`).
- **Data Condition**: Based on workflow input data (e.g., `input.tech_stack == "spring-boot"`).
- **Policy Condition**: Based on organizational policy (e.g., `project.risk_level >= HIGH`).
- **Budget Condition**: Based on remaining budget (e.g., `project.remaining_budget > 0`).
- **Time Condition**: Based on elapsed time or scheduling (e.g., `elapsed_time < 24h`).

## Evaluation Rules
- Conditions are evaluated at the moment a transition is considered, not when the workflow was defined.
- Conditions must be deterministic and side-effect free. They must not trigger tool executions or modify state.
- If a condition references a step output that does not yet exist (because the step hasn't run), the condition evaluates to `false`.
