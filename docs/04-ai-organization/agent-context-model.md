# Agent Context Model

The Context Engine is responsible for assembling the precise LLM prompt (the Context) before the agent starts executing.

## Context Assembly Rules

1. **Scoping**: Context must be scoped tightly. A Database Engineer does not need the CSS guidelines in its context window.
2. **Components**:
   - **Role Definition**: The baseline system prompt defining the agent's persona.
   - **Permissions**: A strict list of authorized tools.
   - **Task Details**: The exact objective and acceptance criteria.
   - **Relevant Artifacts**: Pointers to the PRD or ADR, or a summarized extraction of them.
   - **Code Snippets**: Only the specific source files relevant to the task, pulled via AST parsing or semantic search.
   - **Relevant Memory**: Top 3 RAG results for past decisions related to the task.

## Avoiding Overload
We must never send the entire repository to an agent. If the context exceeds a safe limit (e.g., 75% of the model's max window), the Context Engine aggressively truncates P3 (Historical Memory) and P2 (Source Code), replacing them with summaries or instructing the agent to use `read_file` to fetch them manually.
