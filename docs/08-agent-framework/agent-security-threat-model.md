# Security Threat Model

The Agent Framework executes non-deterministic code (LLM outputs) that manipulates enterprise infrastructure. This requires a robust threat model.

## Threat Catalog

| Threat | Attack Scenario | Impact | Likelihood | Detection | Mitigation | Residual Risk |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Prompt Injection** | Malicious text in a GitHub issue commands the agent to leak data. | High | High | Behavioral monitoring | Context Engine structural delimiters; Sandboxing. | Medium |
| **Tool Abuse** | Agent hallucinates parameters and accidentally runs `rm -rf /`. | Critical | Medium | Tool validation failures | Ephemeral sandboxing; Permission matrices. | Low |
| **Privilege Escalation** | Agent modifies its own definition in the Registry to grant itself `DEPLOY` rights. | Critical | Low | Audit log alerts | Agents lack write access to the Registry. | Low |
| **Context Poisoning** | Attacker modifies a README to include false security policies. | Medium | Medium | Provenance tracking | System policies override all retrieved context. | Low |
| **Secret Exposure** | Agent asks the LLM to output an AWS key it found in a file. | Critical | Medium | Data loss prevention (DLP) scanners | Secrets injected JIT into tools, hidden from LLM. | Low |
| **Cross-Tenant Leakage** | Agent queries data from Tenant B while working on Tenant A. | Critical | Low | RLS DB alerts | Tenant IDs hardcoded at the Context DB layer. | Low |
| **Unauthorized Delegation** | Junior Agent delegates architecture to itself to bypass review. | High | Low | Workflow alerts | Framework enforces strict hierarchical delegation rules. | Low |
| **Data Destruction** | Agent drops the production database. | Critical | Low | Approval timeouts | `CRITICAL` risk tools require explicit human approval. | Low |
| **Cost Abuse (Infinite Loop)** | Agent gets stuck retrying a failing test 10,000 times. | Medium | High | Billing alerts | Strict `TokenBudgets` and `MaxRetries`. | Low |
