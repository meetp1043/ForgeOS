# Context Architecture

The Context Architecture defines how the system assembles the optimal prompt for an agent. 

## Assembly Pipeline
When an agent is initialized, the Context Engine gathers:
1. **User Request**: The immediate command.
2. **System Constraints**: `AGENTS.md` and governance rules.
3. **Task Information**: Dependencies and acceptance criteria.
4. **Memory**: RAG (Retrieval-Augmented Generation) query against the Project Memory for relevant past decisions.
5. **Codebase**: Semantic search against the Git repository to pull in relevant source code snippets.

## Prioritization and Limits
LLM context windows are finite. The Context Engine uses a sliding window and prioritization queue:
- **P0**: System Prompt and immediate Task definition (Never truncated).
- **P1**: User overrides (Never truncated).
- **P2**: Relevant source code (Truncated if too large).
- **P3**: Historical memory (Aggressively summarized or dropped).
