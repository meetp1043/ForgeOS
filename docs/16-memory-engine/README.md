# Memory Engine (Phase 16)

The ForgeOS Memory Engine solves the problem of "Agent Amnesia". Rather than continually pasting entire chat histories into context windows, agents extract structured memories, validate them, and persist them via a hybrid search (keyword + semantic vector) system.

## Architecture

```mermaid
flowchart TD
    Source[Agent / Workflow / Document]
    Source --> Extractor[Memory Extractor]
    Extractor --> Candidate[Memory Candidate]
    
    Candidate --> Validator[Validator (Secrets / Scope)]
    Validator --> DB[(PostgreSQL)]
    
    DB --> Index[pgvector / TSVECTOR]
    
    Query[Agent Query] --> Search[Memory Search Engine]
    Search --> Filter[Tenant & Project Filters]
    Filter --> Index
    
    Index --> Result[Memory Context]
    Result --> Agent[Agent Runtime]
```

## Guiding Principles

1. **No Amnesia**: Important decisions and project constraints must outlive a single execution.
2. **Authority Trumps Confidence**: An AI model might say it is `HIGH` confidence that a database is MongoDB. If a Human Approved memory says it is PostgreSQL, the Human Approved memory wins regardless of vector similarity.
3. **No Unstructured Dumps**: The Memory Engine returns precise facts and documents, not 50-page conversation logs.
