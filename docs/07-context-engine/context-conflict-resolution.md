# Conflict Resolution

The Context Engine aggregates data from diverse, sometimes contradictory sources. It must resolve these conflicts *before* handing the package to the model, preventing the LLM from becoming confused.

## Resolution Logic

The Context Engine resolves conflicts by strictly comparing the `Authority` and `Freshness` of the conflicting candidates.

### The Rule of Unsilent Resolution
**Do not silently merge contradictory information.**

If the Context Engine detects a conflict, it must pick a winner and discard the loser, OR include both but explicitly tell the model which one is authoritative.

### Example Scenario
- **Source A (Old Memory)**: "MongoDB is used for user data."
- **Source B (Approved Architecture Document)**: "PostgreSQL is now the primary database for all services."

**Resolution**: Source B wins. `Approved Architecture` outranks `Old Memory` in [Authority](context-authority.md), and it is newer. The Context Package will instruct the agent to use PostgreSQL. 

If Source A is included at all (e.g., because the agent is migrating data), it must be explicitly labeled as the *superseded/legacy* state to avoid confusing the model.
