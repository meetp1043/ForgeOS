# Deduplication

Context Deduplication prevents the prompt from being flooded with identical or highly similar information retrieved from multiple disparate sources.

## Duplication Scenarios
- **Code**: A function definition might be retrieved via Git search, via an imported dependency trace, and via a previous tool output error log.
- **Memory**: A conversation summary might state "We decided to use PostgreSQL", while the formal ADR also states "Database: PostgreSQL".
- **Documents**: A PRD and a README might contain the exact same paragraph describing the product features.

## Deduplication Logic
1. **Hash Matching**: Exact string matches (e.g., identical code blocks) are deduplicated instantly.
2. **Semantic Matching**: Highly similar vector embeddings are flagged.

## The Authority Rule
**Do not remove information merely because two sources look similar if their authority differs.**

If a casual chat log says "Use Postgres" and an approved ADR says "Use Postgres", do *not* deduplicate by keeping the chat log and dropping the ADR. Always retain the source with the higher `Authority` score and drop the lower-authority duplicate. The agent needs to know that this is a formal rule, not just a casual suggestion.
