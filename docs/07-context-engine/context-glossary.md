# Glossary

Standard definitions used throughout the ForgeOS Context Engine specification.

- **Context**: The specific, temporary package of information assembled for an agent to execute a single task.
- **Context Engine**: The ForgeOS subsystem responsible for discovering, filtering, ranking, and assembling Context.
- **Context Request**: The payload initiating the assembly process, defining the task, agent role, and scope boundaries.
- **Context Package**: The final payload delivered to the Agent Runtime, containing instructions and data.
- **Context Item**: A specific block of data (e.g., a file, an ADR) included in the package.
- **Context Source**: The backend system (Git, Memory Engine, Jira) that provided a candidate item.
- **Context Candidate**: Information discovered during search but not yet approved for the final package.
- **Context Scope**: The boundary (Tenant, Project, Workspace) within which information is valid and isolated.
- **Context Authority**: The hierarchical trust level of an item (e.g., Human Approved > Agent Inferred).
- **Context Relevance**: How useful an item is for solving the immediate objective.
- **Context Ranking**: The algorithmic sorting of candidates based on Authority, Relevance, and Freshness.
- **Context Budget**: The maximum allowable token limit for the package, dictated by the Model Router and cost constraints.
- **Context Compression**: Techniques (summarization, truncation) to fit data within the budget.
- **Context Summarization**: Using an LLM to distill a large document into core facts.
- **Context Deduplication**: Removing redundant information from the package.
- **Context Freshness**: The acceptable age of an item before it is deemed unsafe.
- **Context Staleness**: When an item violates freshness policies and must be refreshed or annotated.
- **Context Cache**: Temporary storage of assembled packages or fragments to reduce latency.
- **Context Invalidation**: The process of clearing the cache when underlying source data changes.
- **Context Isolation**: The strict enforcement of Tenant, Project, and Permission boundaries.
- **Context Provenance**: The auditable trace indicating exactly where an item came from.
- **Context Validation**: The final security and sanity check before package delivery.
- **Context Poisoning**: The introduction of false data into source systems intended to manipulate agent decisions.
- **Prompt Injection**: A malicious string embedded in retrieved data attempting to override system instructions.
- **Context Recall**: The metric defining whether all *required* information was successfully found and included.
- **Context Precision**: The metric defining whether the included information was actually *useful* (minimal noise).
