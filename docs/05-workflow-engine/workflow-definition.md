# Workflow Definition

A Workflow Definition is the immutable blueprint describing a class of workflows. It is the template from which Workflow Instances are created.

## Schema

| Field | Type | Description |
| :--- | :--- | :--- |
| `definition_id` | UUID | Unique identifier for this definition. |
| `version` | SemVer | The version of this definition. |
| `name` | String | Human-readable name (e.g., "New Project Workflow"). |
| `description` | String | Purpose and scope of this workflow. |
| `trigger` | Object | What initiates this workflow (e.g., `USER_COMMAND`, `WEBHOOK`, `SCHEDULE`). |
| `inputs` | Schema | The required inputs to start the workflow (e.g., project name, tech stack). |
| `outputs` | Schema | The expected outputs upon completion (e.g., deployed URL, Git repo). |
| `steps` | List | Ordered list of `WorkflowStep` definitions. |
| `transitions` | List | Rules governing movement between steps. |
| `conditions` | List | Boolean expressions evaluated during transitions. |
| `policies` | Object | Retry, timeout, cost, and approval policies. |
| `required_permissions` | List | The minimum permissions a user must have to trigger this workflow. |

## Immutability
Once a version of a definition is published, it must not be modified. Changes produce a new version. This ensures that a running instance always references a stable, unchanging definition.

## Registration
Definitions are stored in the ForgeOS database and are managed through the administrative interface. They are not ad-hoc files discovered at runtime.
