# Workflow Architecture

ForgeOS utilizes a Workflow Engine for durable execution of long-running tasks.

## Concepts
- **Workflow**: A directed acyclic graph (DAG) of steps representing a project phase (e.g., "Deployment Workflow").
- **Workflow Definition**: The template of the DAG.
- **Workflow Instance**: A specific execution of the definition.
- **Step**: A single unit of work (e.g., "Compile Code", "Wait for Human Approval").
- **Transition & Condition**: Logic dictating how execution moves from Step A to Step B.
- **Retry / Timeout**: Configurations preventing workflows from hanging indefinitely.
- **Compensation**: "Undo" logic triggered if a workflow fails downstream (e.g., rolling back a database migration if the app deployment fails).

## Durability
Because AI generation takes time, workflows must be durable. If the ForgeOS core API container restarts, the workflow engine must resume exactly where it left off, rehydrating the state from the relational database.
