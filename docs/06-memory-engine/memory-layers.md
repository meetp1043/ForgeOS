# Memory Layers

ForgeOS memory is conceptually structured into layers, representing the gradient from immediate, transient context to long-term, permanent knowledge.

Information naturally flows outward through these layers as it is validated, summarized, and deemed broadly applicable. Conversely, it is retrieved inward into the working context when needed.

## Layer 1: Immediate Working Memory
- **Definition**: The active, highly volatile context window currently available to an executing agent.
- **Content**: The immediate task prompt, recent tool execution results, current code snippets, and active conversation turns.
- **Lifecycle**: Exists only for the duration of the current agent interaction or workflow step. 
- **Transition**: Important facts discovered here must be explicitly extracted and committed to higher layers, otherwise they are lost when the context window clears.

## Layer 2: Task Memory
- **Definition**: Information relevant to a specific, ongoing unit of work (a task or workflow).
- **Content**: Task requirements, intermediate compilation results, test failure messages, and sub-agent handoffs.
- **Lifecycle**: Persists until the task or workflow is marked `COMPLETED`, `FAILED`, or `CANCELLED`.
- **Transition**: Upon task completion, key outcomes (e.g., a bug was fixed, a decision was made) are summarized and promoted to Project Memory. The raw task memory is archived or allowed to expire.

## Layer 3: Project Memory
- **Definition**: The durable knowledge base isolated to a specific software project.
- **Content**: Architecture decisions (ADRs), business requirements, project-specific coding conventions, and aggregate failure memories.
- **Lifecycle**: Persists for the lifetime of the project.
- **Transition**: If a project convention or solution is deemed universally useful (e.g., a new security pattern), a Principal Architect agent or human may promote it to Organizational Memory.

## Layer 4: Organizational Memory
- **Definition**: The highest level of shared, active knowledge within a tenant.
- **Content**: Company-wide engineering principles, approved tech stacks, global security policies, and standard operating procedures.
- **Lifecycle**: Indefinite. Subject to strict governance and human oversight.
- **Transition**: Rarely transitions. Mostly updated via formal governance workflows.

## Layer 5: Long-term Historical Memory
- **Definition**: Archived, superseded, or highly compressed knowledge that is no longer needed for daily active reasoning but is retained for audit, rollback, or deep historical context.
- **Content**: Superseded architectural decisions, old workflow execution summaries, and retired agent performance metrics.
- **Lifecycle**: Determined by data retention policies and legal/compliance requirements.
- **Retrieval**: Rarely retrieved by default. Requires explicit "historical" or "deep search" flags in the retrieval query.
