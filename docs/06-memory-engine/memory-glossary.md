# Memory Glossary

Standard definitions used throughout the ForgeOS Memory Engine specification.

- **Memory**: Persisted information intentionally retained by ForgeOS for future retrieval and reasoning.
- **Context**: The temporary, immediate information injected into an agent's prompt during execution.
- **Working Memory**: See Context. Layer 1 of the Memory Hierarchy.
- **Semantic Memory**: Factual, conceptual knowledge disconnected from specific events (e.g., "The backend uses Spring Boot").
- **Episodic Memory**: Event-oriented memory tied to a specific time and context (e.g., "The build failed yesterday").
- **Procedural Memory**: Reusable knowledge about how to perform tasks (e.g., "Run Maven clean install before pushing").
- **Decision Memory**: Formal records of authoritative choices (e.g., ADRs).
- **Project Memory**: The aggregate knowledge bounded strictly to a specific software project.
- **User Memory**: Preferences and instructions tied to a human user identity.
- **Agent Memory**: Experiences and strategies learned by a specific AI agent instance.
- **Organizational Memory**: High-level, cross-project policies shared by an entire tenant.
- **Artifact Memory**: Semantic indexes tied strictly to an authoritative document or file.
- **Memory Entry**: The fundamental, conceptual unit of stored knowledge in ForgeOS.
- **Memory Scope**: The boundary (e.g., Tenant, Project, Task) defining where a memory applies and can be accessed.
- **Memory Provenance**: The auditable chain of custody detailing exactly where a piece of information originated.
- **Memory Confidence**: A score (`HIGH`, `MEDIUM`, `LOW`) indicating the reliability of the memory.
- **Memory Authority**: The hierarchical weight assigned based on who/what generated the memory (e.g., Human > Agent).
- **Memory Retrieval**: The pipeline for formulating queries, filtering candidates, and assembling context.
- **Memory Ranking**: Sorting retrieved candidates by relevance, authority, and recency.
- **Memory Supersession**: The act of replacing an old decision with a new one while maintaining the historical record.
- **Memory Expiration**: The natural aging out (TTL) of transient information.
- **Memory Retention**: The policy dictating how long a specific type of memory is kept in storage before archiving or deletion.
