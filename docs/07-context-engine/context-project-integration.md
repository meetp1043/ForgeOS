# Project Integration

Project-level context provides the overarching framework within which an agent operates. It establishes the "ground rules" for all tasks within that project.

## Project Context Elements
When assembling a Context Package, the engine may include project-level elements such as:
- **Project Objective**: The high-level goal of the software.
- **Technology Stack**: (e.g., "Java 21, Spring Boot, PostgreSQL").
- **Constraints**: (e.g., "Must deploy to AWS", "Maximum 100ms latency").
- **Team Structure**: Known human owners or agent roles.
- **Architecture**: Broad system topology.
- **Requirements**: Active PRDs.
- **Conventions**: Coding standards (e.g., "Use 2 spaces for indentation").
- **Known Risks**: Documented technical debt or security warnings.

## Truncation
Because Project Context is broad, it is highly susceptible to truncation or compression if the token budget is tight. Often, these elements are summarized into a dense 100-token system prompt block rather than injecting full documents.
