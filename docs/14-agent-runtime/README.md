# Agent Runtime Framework (Phase 14)

The ForgeOS Agent Runtime controls the lifecycle, execution, constraints, and reasoning loop of specialized AI workers. It sits between the Model Gateway (the brains) and the Tool System (the hands).

## Agent Organization Diagram

```mermaid
flowchart TD
    User([User / System]) --> PM[Project Manager Agent]
    
    PM -->|Decomposes Objective| Arch[Software Architect]
    PM -->|Decomposes Objective| BA[Business Analyst]
    
    Arch -->|Delegates Architecture| BE[Backend Engineer]
    Arch -->|Delegates Architecture| FE[Frontend Engineer]
    
    BE -->|Code Complete| QA[QA Engineer]
    BE -->|Code Complete| Sec[Security Engineer]
    BE -->|Code Complete| CR[Code Reviewer]
    
    QA -->|Tests Passing| DO[DevOps Engineer]
    Sec -->|Audit Passed| DO
    CR -->|Approval| DO
```

All agents operate strictly through governed systems:
- **Model Gateway**: For reasoned decisions.
- **Tool System**: For physical changes.
- **Approval System**: For high-risk deployments.
