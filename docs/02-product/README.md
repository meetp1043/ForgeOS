# ForgeOS Product Specification

## Purpose
This directory contains the definitive product specifications for ForgeOS. It translates our company vision into concrete product requirements, features, user experiences, and functional models.

## Documentation Hierarchy
- **Product Overview & UX**: High-level concepts, journeys, and experiences (`product-overview.md`, `user-journeys.md`, `conversation-experience.md`).
- **Feature & Scope Definitions**: Catalogs, MVP, and future scopes (`feature-catalog.md`, `mvp-scope.md`).
- **Requirements**: Functional and non-functional requirements (`functional-requirements.md`, `non-functional-requirements.md`).
- **Conceptual Models**: Data and operational models (`project-model.md`, `task-model.md`, `artifact-model.md`).

## Relationship to Company Foundation
These product specifications are directly derived from the principles established in `/docs/01-company/`. If a product feature contradicts our core values or engineering philosophy, the feature must be revised.

## Relationship to Future Architecture Documentation
This directory defines *what* ForgeOS does and *how it behaves* from a user perspective. Future architecture documentation (in `/docs/03-architecture/`) will define *how* these product requirements are technically implemented. Architecture must strictly satisfy these product requirements.

## Product Source-of-Truth Rules
These documents are the authoritative source of truth for the ForgeOS product. 
- No feature should be implemented if it is not described here.
- If an agent or UI component behaves differently than specified here, it is a bug.
- Changes to the product behavior must first be documented here.
