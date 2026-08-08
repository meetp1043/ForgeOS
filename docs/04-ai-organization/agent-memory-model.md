# Agent Memory Model

Agents do not have unrestricted memory access to the entire history of the company. Access is strictly scoped to prevent hallucination and token waste.

## Memory Types

1. **Agent Working Memory (Short-Term)**
   - The immediate LLM context window of the current execution loop.
   - Contains the system prompt, the assigned task, and the output of the last few tool calls.
   - Cleared completely when the task is completed or failed.

2. **Task Memory (Mid-Term)**
   - The durable record of the task execution (the specific tool logs and thought blocks).
   - Saved in the Orchestrator database for audit and evaluation.

3. **Project Memory (Long-Term)**
   - The vector database (RAG) containing resolved architectural decisions (ADRs), product requirements (PRDs), and codebase snippets for the specific project.
   - Accessed selectively via semantic search by the Context Engine.

4. **Organizational Knowledge (Global)**
   - Global templates, rules (e.g., `AGENTS.md`), and historical precedents across projects.
   - Only Managers and Architects typically query this level.

5. **Decision History**
   - Explicitly tracked "Why" records. When a human or Architect makes a decision, it is embedded here so future agents don't re-litigate closed debates.
