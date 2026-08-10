# Memory Types

ForgeOS organizes memory into distinct types, categorized by the *nature* of the information and the *domain* it applies to.

## Cognitive Memory Types (By Nature)

### 1. Semantic Memory
- **Purpose**: Knowledge-oriented, factual information about the state of the world, technology, or system.
- **Examples**: "ForgeOS uses Spring Boot." "The project requires multi-tenancy." "Production deployment requires human approval."
- **Owner**: System / Organization
- **Scope**: Organization / Project
- **Retention**: Long-term
- **Typical Retrieval**: Conceptual queries, architecture reviews.

### 2. Episodic Memory
- **Purpose**: Event-oriented memory tied to a specific time, place, and context.
- **Examples**: "Backend deployment failed on Tuesday because the health check timed out." "Architect rejected Redis for this workload during PR review #45."
- **Owner**: Agent / Workflow
- **Scope**: Task / Project
- **Retention**: Medium-term (expires when no longer relevant to current state).
- **Typical Retrieval**: Root cause analysis, post-mortems.

### 3. Procedural Memory
- **Purpose**: Reusable knowledge about *how* to perform tasks. It must never bypass current project policies.
- **Examples**: "Use Maven wrapper for this project." "Run integration tests before staging deployment."
- **Owner**: Organization / Agent
- **Scope**: Organization / Project
- **Retention**: Long-term
- **Typical Retrieval**: Agent planning phase, task execution setup.

### 4. Decision Memory
- **Purpose**: A structured record of important, authoritative decisions made during the project lifecycle.
- **Examples**: ADRs, technology selections, API design approvals.
- **Owner**: Project / Principal Architect
- **Scope**: Project
- **Retention**: Indefinite (maintains history of superseded decisions).
- **Typical Retrieval**: Architectural planning, code review guardrails.

---

## Domain Memory Types (By Domain)

### 5. Project Memory
- **Purpose**: Domain-specific knowledge isolated to a single software project.
- **Examples**: Project requirements, business rules, coding conventions, known issues, technical constraints.
- **Owner**: Project
- **Scope**: Project
- **Retention**: Indefinite (tied to project lifecycle).

### 6. User Memory
- **Purpose**: Preferences, habits, and instructions tied to a specific human user. Must respect privacy controls and never infer sensitive personal attributes.
- **Examples**: Preferred programming language, UI layout preferences, communication style.
- **Owner**: User
- **Scope**: User
- **Retention**: Indefinite (until user deletion).

### 7. Agent Memory
- **Purpose**: Experience and specialized knowledge acquired by an individual agent instance. Must not override project requirements.
- **Examples**: Previous tasks completed, successful problem-solving strategies, known tool failures, performance metrics.
- **Owner**: Agent
- **Scope**: Agent / Task
- **Retention**: Varies (some expires with task, some persists to improve agent).

### 8. Organizational Memory
- **Purpose**: Broad knowledge shared across all projects within a tenant or company.
- **Examples**: Company-wide engineering standards, security policies, approved technology patterns.
- **Owner**: Organization
- **Scope**: Organization (Tenant)
- **Retention**: Indefinite.

### 9. Artifact Memory
- **Purpose**: Memory strictly tied to and indexing authoritative artifacts.
- **Examples**: Extracting facts from an Architecture document, PRD, Code review, Test report.
- **Owner**: Project
- **Scope**: Artifact / Project
- **Retention**: Tied to the artifact's existence.

### 10. Conversation Memory
- **Purpose**: Distilled facts, instructions, and agreements extracted from human-agent dialogue. Not every chat message is retained; only validated candidate extractions are stored.
- **Examples**: "User requested to switch the theme to Dark Mode during the planning chat."
- **Owner**: User / Agent
- **Scope**: Conversation / Project
- **Retention**: Medium-term (summarized and rolled up over time).

### 11. Workflow Memory
- **Purpose**: Knowledge about orchestrations, states, and transitions.
- **Examples**: What agents executed a pipeline, what approvals were granted, what decisions were routed.
- **Owner**: Workflow Engine
- **Scope**: Workflow / Project
- **Retention**: Short to Medium-term (for audit and recovery).

### 12. Failure Memory
- **Purpose**: Operational learning from previous failures to prevent recurrence. Must be validated and contextualized so it does not become an unquestioned, permanent block.
- **Examples**: Known dependency conflicts, frequent deployment timeouts, model failure modes.
- **Owner**: Organization / Agent
- **Scope**: Organization / Project
- **Retention**: Medium to Long-term.
