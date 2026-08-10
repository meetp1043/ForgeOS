# Context Engine Principles

The Context Engine operates strictly according to the following principles to guarantee accuracy, security, and efficiency.

1. **Minimum sufficient context.** Provide exactly what is needed to solve the task, and nothing more. Context pollution degrades reasoning and inflates costs.
2. **Security before relevance.** A highly relevant credential or cross-tenant document must be blocked if the agent lacks authorization. Security filters execute before semantic ranking.
3. **Authority before convenience.** An approved Architecture Decision Record (ADR) always overrides a casual conversation summary, even if the conversation is newer or semantically closer.
4. **Relevant information over complete information.** Do not send the entire repository when only a single module and its interfaces are required.
5. **Explicit instructions over assumptions.** Task context must provide clear success criteria rather than leaving the agent to guess the goal.
6. **Approved decisions over suggestions.** Context should clearly differentiate between authoritative project policies and mere historical ideas.
7. **Fresh information over stale information.** Context regarding system state, Git branches, or CI/CD status must be real-time.
8. **Project isolation.** Context must not leak from Project A to Project B unless explicitly authorized.
9. **Tenant isolation.** Context must absolutely never leak across tenants.
10. **Agent least privilege.** An agent only receives the context necessary for its assigned role (e.g., a Database Engineer agent does not need frontend CSS tokens).
11. **Data and instructions must remain distinguishable.** Retrieved text (like a README) must be structurally separated from system instructions to prevent prompt injection.
12. **Context must be auditable.** A human operator must be able to see exactly what context was provided to an agent during a specific task execution.
13. **Context must be measurable.** We must track metrics like Context Precision, Recall, and Token Efficiency.
14. **Context must be model-aware.** Assembly must adapt to the target model's token limits and formatting capabilities.
15. **Context must be cost-aware.** Expensive retrievals and massive context injections must be budgeted.
16. **Context must degrade safely.** If a non-critical source fails, assemble the package without it rather than crashing, but warn the agent.
17. **Missing critical context must block high-risk execution.** If an authorized deployment policy cannot be retrieved, a deployment agent must fail closed.
18. **Context should be reproducible where practical.** For a given git commit and memory state, the Context Engine should ideally produce the same Context Package for deterministic debugging.
