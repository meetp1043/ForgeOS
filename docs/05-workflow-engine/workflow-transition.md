# Workflow Transitions

Transitions define the directed edges between steps in the workflow DAG.

## Transition Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `transition_id` | UUID | Unique identifier. |
| `from_step` | UUID | The source step. |
| `to_step` | UUID | The destination step. |
| `trigger` | Enum | What causes this transition to fire. |
| `condition` | Expression (nullable) | Optional boolean expression that must evaluate to `true`. |
| `priority` | Integer | When multiple transitions are eligible, the highest priority wins. |

## Trigger Types

| Trigger | Description |
| :--- | :--- |
| `ON_SUCCESS` | The source step completed successfully. |
| `ON_FAILURE` | The source step failed (after all retries). |
| `ON_CONDITION` | A boolean condition evaluates to true. |
| `ON_APPROVAL` | A human approved the pending request. |
| `ON_REJECTION` | A human rejected the pending request. |
| `ON_TIMEOUT` | The source step exceeded its timeout. |
| `ON_MANUAL` | A human explicitly selected this transition path. |

## Ambiguity Prevention
If multiple transitions from the same step are eligible simultaneously, the engine evaluates them in priority order and takes only the first matching transition. If no transition matches, the workflow enters `BLOCKED` state and escalates.
