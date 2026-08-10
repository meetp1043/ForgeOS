# Workflow Memory

Workflow Memory acts as the state and history tracker for the ForgeOS Orchestrator. It allows the system to understand *what* it is currently doing, and *what* it has just done.

## Definition
Knowledge about orchestrations, states, transitions, agent assignments, and workflow execution paths.

## Examples
- "The `Project Initialization` workflow has successfully completed Phase 1 (Requirements) and is currently executing Phase 2 (Architecture) in parallel with Phase 3 (Infrastructure)."
- "The Senior Backend Engineer agent was assigned to Task #45, but failed and was replaced."
- "Human approval was granted for the staging deployment at 10:00 AM."

## Purpose
Workflow Memory is essential for:
1. **Idempotency**: Ensuring steps are not repeated if the orchestrator crashes.
2. **Resumption**: Allowing a paused workflow to resume with full context.
3. **Audit**: Proving that specific gates (like security reviews) were passed before deployment.

## Characteristics
- **Owner**: Workflow Engine
- **Scope**: Workflow / Project
- **Retention**: Short to Medium-term (Heavily active during execution; archived upon workflow completion).
- **Access**: Orchestrator, supervisory agents, and audit logs.
