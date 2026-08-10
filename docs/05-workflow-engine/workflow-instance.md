# Workflow Instance

A Workflow Instance is a single, concrete execution of a Workflow Definition.

## Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `instance_id` | UUID | Unique identifier for this execution. |
| `definition_id` | UUID | Reference to the parent Workflow Definition. |
| `definition_version` | SemVer | The exact version of the definition being executed. |
| `project_id` | UUID | The ForgeOS project this workflow belongs to. |
| `tenant_id` | UUID | The owning tenant (for multi-tenancy isolation). |
| `owner_id` | UUID | The user who triggered this workflow. |
| `status` | Enum | Current lifecycle state (see `workflow-lifecycle.md`). |
| `current_steps` | List | Step instance IDs that are currently active. |
| `execution_history` | List | Ordered log of all step transitions. |
| `context_ref` | UUID | Reference to the assembled workflow context. |
| `input_data` | Object | The actual input values provided at trigger time. |
| `output_data` | Object (nullable) | The final outputs upon completion. |
| `created_at` | Timestamp | When the instance was created. |
| `updated_at` | Timestamp | Last state transition time. |
| `completed_at` | Timestamp (nullable) | When the workflow reached a terminal state. |
| `failure_info` | Object (nullable) | Details of the failure if status is `FAILED`. |
| `version` | Long | Optimistic locking version for concurrency control. |

## Instance Isolation
Each instance is fully independent. Two instances of the same definition running for different projects do not share state, context, or agent assignments.
