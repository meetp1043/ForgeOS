# ForgeOS Documentation

This directory contains the authoritative documentation for ForgeOS.

## Documentation Purpose
To provide a single source of truth for all human and AI agents working on ForgeOS, detailing governance, architecture, design, and operations.

## Documentation Hierarchy
- `/docs/00-governance/`: Rules, standards, and processes.
- `/docs/01-company/` to `/docs/23-roadmap/`: Domain-specific documentation.

## Source-of-Truth Rules
- Documents in this repository are the definitive source of truth.
- If code and documentation conflict, the conflict must be resolved immediately by updating the documentation to reflect intentional changes, or fixing the code to match the required architecture.

## Document Ownership
- All documentation is collectively owned.
- Specialized agents (e.g., Solution Architect, Technical Writer) are responsible for maintaining their respective domain documentation.

## Versioning
- Documentation is versioned alongside the code in Git.

## Review and Approval Process
- All documentation changes must be reviewed and approved via Pull Request.
- High-impact changes require human CTO/Principal Engineer approval.

## Architecture Decision Records (ADR)
- Located in their respective domain folders or a centralized `/docs/architecture/adrs/` folder.
- Must follow the template in `/templates/architecture-decision-record.md`.

## Change Management
- AI agents must read relevant documentation before implementing changes.
- Ensure the `change-management.md` process is followed.

## How AI Agents Should Consume Documentation
1. Before starting a task, search and read relevant documents in `/docs/`.
2. Apply constraints and principles to the planned implementation.
3. Update documents if the task introduces new patterns or modifies existing architecture.
