# Agent Runtime & Orchestration (Phase 21)

The ForgeOS Agent Runtime controls the lifecycle, execution loop, state transitions, and budget constraints of all intelligent agents. It enforces the critical rule that agents do not execute external actions directly—they must request tools through the Phase 20 Tool & Capability System.

## Core Architecture

### The State Machine
The agent execution follows a strict state machine implemented in the `StateTransitionEngine`. This ensures deterministic behavior and prevents invalid transitions (e.g., a `COMPLETED` execution resuming, or jumping straight to `EXECUTING` without `PLANNING`).

Valid States: `CREATED`, `INITIALIZING`, `PLANNING`, `EXECUTING`, `WAITING_FOR_TOOL`, `WAITING_FOR_APPROVAL`, `OBSERVING`, `REPLANNING`, `VALIDATING`, `COMPLETING`, `COMPLETED`, `FAILED`, `CANCELLED`, `TIMED_OUT`, `PAUSED`, `QUARANTINED`.

### The Execution Loop
The `AgentExecutionLoop` drives the state machine forward. It evaluates the agent's current state and triggers transitions until the execution hits a terminal state (`COMPLETED`, `FAILED`, `CANCELLED`) or an async blocking state (`WAITING_FOR_APPROVAL`).

### Budget Enforcement
Every agent receives an `AgentBudget` upon creation. During the execution loop, the budget is checked. If it is exhausted (too many tokens or tool calls), the execution is forcibly transitioned to `FAILED`.

## Security & Integration
The Agent Runtime acts as the trusted orchestrator. When an agent (via the Model Gateway) decides to take an action, it issues an `AgentPlan` containing a `PlanStep` with a specific tool request. The Runtime intercepts this, invokes the `ToolAuthorizationEngine` (Phase 20), and if authorized, invokes the Secure Execution Sandbox (Phase 19) or Git Engine (Phase 18).
