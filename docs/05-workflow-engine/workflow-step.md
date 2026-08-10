# Workflow Step

A Workflow Step is the fundamental unit of work within a workflow.

## Step Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `step_id` | UUID | Unique identifier. |
| `type` | Enum | The step type (see below). |
| `name` | String | Human-readable name (e.g., "Implement User Authentication API"). |
| `description` | String | What this step accomplishes. |
| `inputs` | Schema | Data required to execute the step. |
| `outputs` | Schema | Data produced upon completion. |
| `agent_role` | String (nullable) | The agent role required (e.g., "Backend Engineer"). |
| `tool` | String (nullable) | Specific tool to invoke (for `TOOL_EXECUTION` steps). |
| `preconditions` | List | Conditions that must be true before this step can start. |
| `retry_policy` | Object | Retry configuration for this step. |
| `timeout` | Duration | Maximum allowed execution time. |
| `approval_policy` | Object (nullable) | Approval requirements (for gated steps). |
| `compensation` | Object (nullable) | Compensating action if downstream steps fail. |
| `status` | Enum | `PENDING`, `READY`, `RUNNING`, `WAITING`, `COMPLETED`, `FAILED`, `SKIPPED`, `CANCELLED`. |

## Step Types

| Type | Description |
| :--- | :--- |
| `AGENT_TASK` | Assigns work to an AI agent for execution. |
| `HUMAN_APPROVAL` | Pauses execution and waits for a human decision. |
| `TOOL_EXECUTION` | Directly invokes a specific tool (e.g., a build command). |
| `CONDITION` | Evaluates a boolean expression to determine the next branch. |
| `PARALLEL` | A container step that executes its children concurrently. |
| `SEQUENTIAL` | A container step that executes its children in strict order. |
| `WAIT` | Pauses until an external event arrives (e.g., webhook). |
| `NOTIFICATION` | Sends a notification to a user or channel. |
| `SUB_WORKFLOW` | Triggers another complete workflow as a child. |
| `VERIFICATION` | Executes validation logic (e.g., running tests, checking build). |
