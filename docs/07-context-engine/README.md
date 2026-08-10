# ForgeOS Context Engine Specification

## Purpose
This directory contains the authoritative specification for the ForgeOS Context Engine. The Context Engine defines how ForgeOS constructs the minimal, authoritative, secure, and highly relevant package of information an AI agent receives for a specific task.

## Scope
The Context Engine governs the transformation of raw data and memory into an actionable **Context Package** injected into an agent's prompt. It answers: *"What information should this specific agent receive for this specific execution?"*

## Documentation Structure
The documentation is conceptually organized into the following areas:
- **Fundamentals & Boundaries**: Overview, principles, boundaries, lifecycle.
- **Data Structures**: Context Request, Context Package, conceptual schemas.
- **Sources & Hierarchy**: Source catalog, source priority, scope, authority, relevance.
- **Processing & Filtering**: Ranking, prioritization, security filtering, permission filtering.
- **Isolation & Scoping**: Tenant, project, workspace, agent, and artifact isolation boundaries.
- **Integration Points**: How context pulls from memory, workflows, tasks, requirements, architecture, code, and tests.
- **Retrieval Types**: Code structure, documents, conversations, history, tools, external systems.
- **Optimization**: Model routing awareness, token budgets, cost control, summarization, deduplication.
- **Operations & Security**: Caching, freshness, conflict resolution, prompt injection defenses, validation, recovery, and observability.

## Relationship with Architecture
The Context Engine implements the core Data and Security principles outlined in `/docs/03-architecture/`. It acts as the intelligent broker between the raw storage layer and the execution layer.

## Relationship with Memory Engine
**MEMORY != CONTEXT.**
- The [Memory Engine](../06-memory-engine/README.md) is the persistent store of knowledge ("What ForgeOS knows").
- The Context Engine retrieves specific elements of Memory to satisfy a Context Request ("What this agent needs right now").

## Relationship with Workflow Engine
The [Workflow Engine](../05-workflow-engine/README.md) orchestrates the task pipeline. When the Workflow Engine dispatches a step, it triggers the Context Engine to assemble the exact state required for that step's execution.

## Relationship with Agent Runtime
The Agent Runtime executes the AI model. The Context Engine provides the structured prompt assembly (System Instructions + Context Package) that the Runtime submits to the Model.

## Relationship with Model Router
The Context Engine cooperates with the Model Router. If a Context Package requires 120,000 tokens, the Context Engine signals the Model Router to select an appropriate long-context model or triggers compression if budget is constrained.

## Relationship with Tool System
The Tool System provides temporary execution context. The Context Engine evaluates tool outputs, ranks them, and decides whether they should be included in the immediate context or truncated.

## Authoritative Precedence
In the event of a contradiction, the following hierarchy applies:
1. `/AGENTS.md`
2. `/docs/00-governance/`
3. Approved architecture decisions in `/docs/03-architecture/`
4. This directory (`/docs/07-context-engine/`)
5. The Memory Engine (`/docs/06-memory-engine/`)
