# Document Retrieval

Static documents provide the "why" and "how" that isn't always apparent in source code.

## Types of Documents Retrieved
The Context Engine retrieves from sources like:
- **READMEs**: General project or module overviews.
- **PRDs (Product Requirement Documents)**: Feature definitions.
- **ADRs (Architecture Decision Records)**: Technical decisions and trade-offs.
- **API Documentation**: OpenAPI/Swagger definitions.
- **Architecture Documentation**: System topologies and diagrams.
- **Deployment Documentation**: Runbooks and release guides.
- **Technical Documentation**: Internal wikis or standard operating procedures.

## Prefer Authoritative Documents
When resolving semantic similarity, the Context Engine must prioritize authoritative, version-controlled documents (like an ADR in the repository) over a loose wiki page or a conversational memory. Documents that have passed a formal review process have higher `Authority` scores in the ranking algorithm.
