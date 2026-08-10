# Conceptual Schema

To operate effectively, the Context Engine relies on a conceptual data model. *(Note: This is a logical domain model, not a physical SQL schema).*

## Core Entities

### ContextRequest
The incoming payload. Contains the `TaskID`, `AgentRole`, `Objective`, and resource budgets.

### ContextPackage
The outgoing payload. Contains the assembled prompt components and metadata about the assembly process (e.g., total tokens used).

### ContextCandidate
Any piece of information discovered during the `DISCOVERING` phase. Candidates are evaluated, ranked, and either promoted to a `ContextItem` or discarded.

### ContextItem
A discrete block of information included in the final `ContextPackage` (e.g., a specific code file, an ADR summary).

### ContextSource
The backend system that provided the candidate (e.g., `MemoryEngine`, `GitRepository`, `ArtifactStore`).

### ContextReference
A lightweight pointer to an item. Used during [Compression](context-compression.md) when the full item exceeds token limits, allowing the agent to retrieve it manually via a tool if necessary.

### ContextPolicy
The ruleset applied during the `FILTERING` phase (e.g., "Frontend Engineers cannot view `secrets.yml`").

### ContextBudget
The constraints applied to the package assembly, detailing token limits for specific categories (e.g., max 20,000 tokens for source code, max 5,000 tokens for memory).

### ContextValidation
The record of the final security and sanity check performed before the package is marked `READY`.

## Relationships
- A `ContextRequest` triggers the creation of many `ContextCandidates`.
- `ContextPolicies` and `ContextBudgets` filter and rank `ContextCandidates`.
- Surviving `ContextCandidates` become `ContextItems`.
- A `ContextPackage` aggregates `ContextItems` and `ContextReferences`.
- `ContextValidation` approves the `ContextPackage`.
