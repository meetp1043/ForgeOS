# Agent Responsibility Model

Responsibility in ForgeOS is strictly siloed to ensure accountability and prevent agents from hallucinating outside their domain.

## Core Principle
If an agent is not explicitly responsible for a domain, it must not attempt to act within it. Instead, it must delegate or escalate.

## Example Scenario: Adding a Database Column
1. **Frontend Engineer** realizes a new feature needs a new field in the API. It *cannot* modify the database itself. It flags the requirement.
2. **Backend Engineer** realizes the database needs updating. It *cannot* write the migration itself. It requests assistance from the Engineering Manager.
3. **Database Engineer** receives the task, analyzes the schema, writes the safe migration script, and submits it for review.
4. **Code Review Engineer** verifies the migration doesn't lock tables dangerously.
5. **Human Administrator** approves the execution (High Risk).
6. **Execution Sandbox** runs the migration.

By adhering to this model, we avoid the classic AI pitfall where a single coding agent tries to do everything and corrupts the system state.
