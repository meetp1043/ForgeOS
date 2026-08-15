# INDEXING EVENTS

## Indexing Architecture

```mermaid
graph TD
    A[Resource Change] --> B[Domain Event]
    B --> C[Event Bus]
    C --> D[Search Index Consumer]
    D --> E[Resource Transformer]
    E --> F[Permission Metadata]
    F --> G[Search Index]
    G --> H[Search API]
```
