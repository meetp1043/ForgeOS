# ForgeOS System Architecture

## Purpose
This directory contains the authoritative system architecture for ForgeOS. It defines the structural foundation that satisfies the functional and non-functional requirements established in `/docs/02-product/`.

## Architecture Principles
We strictly follow a Modular Monolith first approach using Spring Modulith. We avoid premature optimization into microservices while enforcing strict module boundaries to allow safe extraction later if operational scaling requires it.

## Documentation Hierarchy
- **System Level**: Overview, Context, Containers (`architecture-overview.md`, `system-context.md`).
- **Module Level**: Internal application boundaries (`module-architecture.md`).
- **Agent Orchestration Level**: Core AI execution systems (`agent-runtime-architecture.md`, `orchestration-architecture.md`, `workflow-architecture.md`).
- **Supporting Systems**: Memory, Tools, Execution, Data, Events.
- **Cross-Cutting Concerns**: Security, Observability, Multi-Tenancy.
- **Decisions & Risks**: Architecture Decision Records (`adr-index.md`), Risk catalog.

## Audience
This documentation is for technical leadership, software architects, and engineers responsible for developing the ForgeOS platform itself. It is not intended for users of ForgeOS.
