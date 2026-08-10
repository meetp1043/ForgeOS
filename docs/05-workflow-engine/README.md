# ForgeOS Workflow Engine Specification

## Purpose
This directory contains the authoritative specification for the ForgeOS Workflow Engine — the durable orchestration system that converts user objectives into coordinated, multi-agent engineering work.

## Core Concept
The Workflow Engine is the backbone of ForgeOS execution. It does not contain business logic (that belongs to agents). Instead, it coordinates work: assigning tasks, enforcing dependencies, managing approvals, handling failures, and ensuring recoverability across long-running software engineering processes.

## Documentation Structure
- **Fundamentals**: Overview, lifecycle, state model, definitions (`workflow-overview.md`, `workflow-lifecycle.md`).
- **Structure**: Steps, transitions, conditions, dependencies (`workflow-step.md`, `workflow-transition.md`).
- **Execution**: Orchestration loop, agent assignment, parallelism (`workflow-execution-model.md`, `workflow-orchestration.md`).
- **Control Flow**: Approvals, human intervention, pause/resume, cancellation (`workflow-approval-gates.md`, `workflow-human-intervention.md`).
- **Resilience**: Retry, timeout, failure handling, compensation, recovery (`workflow-retry-policy.md`, `workflow-recovery.md`).
- **Governance**: Versioning, idempotency, concurrency, security, audit (`workflow-versioning.md`, `workflow-audit.md`).
- **Operations**: Observability, cost control (`workflow-observability.md`, `workflow-cost-control.md`).
- **Templates & Examples**: Reusable workflow templates and concrete examples (`workflow-templates.md`, `workflow-examples.md`).
- **Reference**: Risks, glossary (`workflow-risks.md`, `workflow-glossary.md`).

## Alignment
This specification is governed by `/AGENTS.md` and is consistent with:
- `/docs/03-architecture/workflow-architecture.md` — Architectural foundations.
- `/docs/03-architecture/orchestration-architecture.md` — Orchestration principles.
- `/docs/04-ai-organization/` — Agent hierarchy, permissions, and delegation rules.
