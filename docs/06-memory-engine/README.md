# ForgeOS Memory Engine Specification

## Purpose
This directory contains the authoritative specification for the ForgeOS Memory Engine. It defines the systems, structures, and policies governing how ForgeOS agents and workflows intentionally retain, store, scope, update, and retrieve information.

## Core Concept
ForgeOS memory is **NOT** model training. Memory consists of persisted information that ForgeOS intentionally stores, retrieves, updates, summarizes, scopes, and audits. It provides long-term, organized knowledge required for engineering tasks, distinctly separated from raw context, temporary logs, artifacts, and conversation history.

## Documentation Structure
- **Fundamentals**: Overview, principles, types, layers (`memory-overview.md`, `memory-principles.md`, `memory-types.md`, `memory-layers.md`).
- **Lifecycle & Modeling**: Entry model, retention, expiration, update policy, deletion, correction, conflict resolution, confidence, provenance (`memory-lifecycle.md`, `memory-entry-model.md`, etc.).
- **Access & Ownership**: Scoping, ownership, permissions, multi-tenancy (`memory-scoping.md`, `memory-permissions.md`, etc.).
- **Memory Types Detail**: Dedicated specs for semantic, episodic, procedural, decision, project, user, agent, organizational, artifact, conversation, workflow, and failure memory.
- **Retrieval & Processing**: Retrieval pipeline, ranking, relevance, vector search, hybrid search, summarization, compression (`memory-retrieval.md`, `memory-hybrid-search.md`, etc.).
- **Infrastructure & Operations**: Storage strategy, caching, consistency, security, privacy, observability, cost control, evaluation, failure recovery, risks, glossary (`memory-storage-strategy.md`, `memory-security.md`, `memory-glossary.md`, etc.).

## Alignment
This specification adheres to:
- `/AGENTS.md` — ForgeOS AI Agent Engineering Constitution.
- `/docs/00-governance/` — Governance, rules, and standards.
- `/docs/03-architecture/` — System architecture principles.
- `/docs/04-ai-organization/` — Agent hierarchy, delegation, and permissions rules.
- `/docs/05-workflow-engine/` — Orchestration boundaries and control flows.
