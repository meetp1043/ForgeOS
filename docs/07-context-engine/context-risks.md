# Context Engine Risks

The operation of the Context Engine carries inherent risks. This document catalogs known failure modes and their structural mitigations.

| Risk | Impact | Likelihood | Mitigation | Residual Risk |
| :--- | :--- | :--- | :--- | :--- |
| **Context Explosion** | High. Context grows beyond token limits, breaking execution. | High | Aggressive `TokenBudget` enforcement, `Compression`, and `Truncation`. | Medium |
| **Incorrect Retrieval** | High. Agent receives irrelevant code and hallucinates a fix. | Medium | Hybrid Search (BM25 + Vector) and strict `Relevance` scoring. | Medium |
| **Missing Information** | Critical. Agent lacks the security rules to operate safely. | Medium | `Validation` phase forces a Fail-Closed if `CRITICAL` context is missing. | Low |
| **Stale Information** | High. Agent overwrites new code with old logic. | Medium | Strict `Freshness` policies and Git state re-fetching. | Low |
| **Security Leakage** | Critical. Secrets or cross-tenant data enter the prompt. | Low | `Security Filtering` and `Tenant Isolation` prior to ranking. | Low |
| **Prompt Injection** | High. Malicious string hijacks the agent. | High | `Instruction vs Data Separation` delimiters. | Medium |
| **Poisoning** | Medium. Deliberate false ADR skews architecture. | Low | `Authority` supremacy (approved docs beat raw data) and `Provenance` tracking. | Low |
| **Token Cost** | Medium. Runaway AWS/OpenAI bills. | High | Explicit `Cost Control`, smaller model routing for simple tasks. | Medium |
| **Latency** | Medium. Agents feel sluggish. | High | `Cache` and lazy/background retrieval. | Medium |
| **Summarization Loss**| High. The LLM summarizer drops a critical detail (e.g., a "NOT"). | Medium | Including original `ContextReferences` so agents can verify summaries. | Low |
| **Ranking Bias** | Medium. The search algorithm favors new noise over old rules. | Low | Tuning the algorithm to prioritize `Authority` over `Recency`. | Low |
| **Model Mismatch** | Medium. Formatting XML for a model that prefers Markdown. | Low | `Model Awareness` and schema adaptation. | Low |
| **Cache Inconsistency**| High. Agent uses an old PRD because cache didn't clear. | Medium | Robust `Cache Invalidation` triggers hooked into Git/DB events. | Low |
| **Excessive Complexity**| Medium. The engine becomes a brittle monolith. | Medium | Aligning strictly with the Spring Modulith bounded context architecture. | Low |
