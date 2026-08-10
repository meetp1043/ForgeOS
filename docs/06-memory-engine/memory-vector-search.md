# Vector Search

Vector search (using dense embeddings) allows ForgeOS to find information based on semantic meaning rather than exact keyword matches.

## Appropriate Uses for Vector Search
Vector embeddings are highly effective for:
- **Semantic Project Knowledge**: "What is our general strategy for handling authentication?"
- **Similar Failures**: Finding past stack traces that look semantically similar to a current bug.
- **Documentation Retrieval**: Finding the right paragraph in a large Artifact based on a natural language query.

## Inappropriate Uses
Vector search should **not** be used for:
- **Exact Identifiers**: Searching for a specific UUID, email address, or exact variable name. (Use Keyword/BM25).
- **Boolean States**: Determining if a feature flag is ON or OFF.
- **Authoritative Decisions**: When asking "Who approved this?", metadata filtering is required, not semantic proximity.

## Constraints
- **Do not embed everything blindly.** Generating embeddings costs time and money. Temporary task states or short-lived logs should not be embedded.
- **Do not require vector search for every memory type.** Simple key-value lookups (e.g., User Preferences) should use standard database queries.
