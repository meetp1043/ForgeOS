# Context Freshness

Freshness policies dictate the acceptable age of a piece of information before it is considered too risky to use without verification.

## Example Freshness Policies

- **Static Architecture (ADRs)**: 
  - *Freshness*: Long (weeks/months). 
  - *Policy*: Assume fresh unless the Memory Engine emits an explicit update event.
- **Git State (Working Directory)**: 
  - *Freshness*: Very short (seconds).
  - *Policy*: Always pull live state on every interaction; never cache.
- **Production Health (Incidents)**: 
  - *Freshness*: Near-zero (milliseconds).
  - *Policy*: Context must be a live query to the observability platform.
- **Credentials/Secrets**:
  - *Freshness*: N/A.
  - *Policy*: Never place into normal context. Rely on just-in-time secret manager injections at the tool execution layer.
