# Task Model

A Task is the atomic unit of work assigned to an agent or a human within ForgeOS.

## Properties
- **ID**: Unique identifier (e.g., TASK-102).
- **Title**: Brief description of the work.
- **Description**: Detailed requirements and context.
- **Priority**: Low, Medium, High, Critical.
- **Status**: The current state of the task in its lifecycle.
- **Owner**: The Project Manager agent or human who created the task.
- **Assigned Agent**: The specific specialist agent responsible for execution (e.g., Backend Developer).
- **Dependencies**: List of Task IDs that must be completed before this task can start.
- **Inputs**: Required artifacts or context files.
- **Outputs**: Expected artifacts to be generated.
- **Acceptance Criteria**: Verifiable conditions that must be met for the task to be marked Done.
- **Execution History**: Log of all agent attempts, thoughts, and tool calls.
- **Retry Information**: Counter of how many times the agent has failed and retried.
- **Approvals**: Record of required and obtained human or reviewer sign-offs.

## Task States
- **TODO**: Task is planned but blocked by dependencies or pending assignment.
- **READY**: Task is assigned and ready for an agent to pick up.
- **IN PROGRESS**: Agent is actively executing the task.
- **REVIEW**: Task is complete but pending Code Reviewer or Human approval.
- **BLOCKED**: Agent is stuck and requires human intervention or missing context.
- **DONE**: Task is successfully completed and merged.
- **FAILED**: Task exceeded retry limits and aborted.
