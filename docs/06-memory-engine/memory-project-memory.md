# Project Memory

Project memory serves as the holistic boundary for all knowledge specific to a single software development effort.

## Definition
The aggregate, domain-specific knowledge isolated to a single software project. It acts as the primary "brain" for the agents assigned to that project.

## Examples
- Architecture patterns.
- Business requirements and user stories.
- Specific coding conventions (e.g., "Always use `snake_case` for database columns").
- Known technical constraints (e.g., "The payment gateway API has a rate limit of 10 req/sec").
- Deployment configurations.

## Characteristics
- **Owner**: Project
- **Scope**: Project (Strict multi-tenancy isolation applies here).
- **Retention**: Indefinite (lives and dies with the project).
- **Access**: Available to all agents assigned to the project. Denied to agents operating on other projects.
- **Typical Retrieval**: Pre-loaded into the "System Context" of agents when they are initialized for a project task.
