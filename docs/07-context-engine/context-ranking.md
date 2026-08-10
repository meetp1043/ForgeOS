# Context Ranking

Once a set of Context Candidates has passed through all mandatory security and permission filters, they must be ranked. Ranking determines which items make it into the final Context Package and which are truncated or relegated to external references.

## The Ranking Algorithm

Ranking is a multi-dimensional calculation. The Context Engine evaluates:

- **Security Classification**: (Though filtered prior, higher sensitivity data that *is* authorized may require careful placement or summarization).
- **Authority**: Does this candidate represent an approved decision?
- **Relevance**: How semantically and structurally related is this candidate to the task?
- **Freshness**: How recently was this state updated?
- **Confidence**: Is this a known fact or an inferred probability?
- **Scope Proximity**: Is this highly localized to the current module, or a broad project-wide rule?
- **Importance**: Did a human or system explicitly flag this rule as critical?
- **Explicit References**: Did the workflow mandate the inclusion of this specific file?
- **Task Dependency**: Does the current task explicitly block on understanding this candidate?

## The Preeminence of Security
**Security filtering happens BEFORE ranking.** The engine must never spend compute ranking a candidate that the agent is not authorized to see, nor should a highly-ranked but unauthorized item ever bump an authorized item out of the budget.
