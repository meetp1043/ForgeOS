# Security Threat Model

The Context Engine operates at the intersection of raw data, persistent memory, and autonomous execution. It is a prime target for exploitation.

## Threat Catalog

| Threat | Impact | Likelihood | Detection | Mitigation | Residual Risk |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1. Cross-Tenant Leakage** | Critical. Exposure of proprietary data to competitors. | Low | Audit logs tracking `TenantID` mismatches. | Hard RLS at the DB layer; mandatory TenantID filtering. | Low |
| **2. Cross-Project Leakage** | High. Exposure of confidential project data to unauthorized internal agents. | Medium | Scope boundary alerts. | Explicit Project filtering. | Low |
| **3. Secret Exposure** | Critical. Agent leaks or misuses production keys. | Medium | Secret scanning pre-assembly. | Just-in-time secret injection at the Tool layer; never in raw context. | Medium |
| **4. Prompt Injection** | High. Agent executes malicious commands hidden in data. | High | Output behavior monitoring. | Strict Data/Instruction separation delimiters. | Medium |
| **5. Context Poisoning** | Medium. Agent acts on false, subtly modified historical data. | Medium | Provenance tracking, confidence drops. | Authority precedence (ADRs override chat). | Low |
| **6. Malicious Repository** | High. Compromised codebase contains injection vectors. | Medium | CI/CD pre-commit scanning. | Treat all code as Data, not Instructions. | Medium |
| **7. Malicious Tool Result** | Medium. E.g., `curl` returns an injection payload. | High | Log analysis. | Treat tool output strictly as Data. | Low |
| **8. Unauthorized Retrieval** | High. Agent bypasses RBAC to view sensitive HR documents. | Low | Permission Audit Logs. | Agent Role-based Permission Filtering. | Low |
| **9. Stale Security Info** | High. Agent bypasses a newly enacted security rule because it retrieved the cached, old rule. | Low | Cache invalidation metrics. | Strict Freshness policies for Security policies. | Low |
| **10. Privilege Escalation** | Critical. Agent uses retrieved context to trick the system into granting it admin rights. | Low | Tool usage monitoring. | Context != Permissions. Context grants no execution rights. | Low |
| **11. Model Confusion** | Medium. LLM fails to parse the context package and hallucinates. | Medium | Quality metrics (LLM-as-a-judge). | Formatting optimized for the specific target model. | Medium |
| **12. Sensitive Logging** | High. The Context Engine audit log writes plain-text PII or secrets to Datadog. | Medium | Log scanning. | Data masking in Observability streams. | Low |
