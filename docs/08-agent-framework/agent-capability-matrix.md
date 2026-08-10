# Agent Capability Matrix

This matrix maps capabilities to the roles that typically possess them.

| Capability | Definition | Backend Eng | Frontend Eng | DBA | QA Eng | Sec Eng | Sol Arch | DevOps |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `CODE_WRITE` | Author implementation logic. | Yes | Yes | Yes (SQL) | Yes (Tests) | No | No | No |
| `CODE_READ` | Parse and understand codebase. | Yes | Yes | Yes | Yes | Yes | Yes | Yes |
| `CODE_REVIEW` | Approve/Reject PRs based on rules. | No | No | No | No | Yes | No | No |
| `TEST_EXECUTION` | Run automated test suites. | Yes | Yes | No | Yes | No | No | Yes |
| `DATABASE_MIGRATION`| Write/Execute DDL. | No | No | Yes | No | No | No | No |
| `ARCHITECTURE_WRITE`| Author ADRs and topologies. | No | No | No | No | No | Yes | No |
| `DEPLOYMENT` | Push artifacts to infrastructure. | No | No | No | No | No | No | Yes |
| `REQUIREMENT_WRITE` | Author PRDs and user stories. | No | No | No | No | No | No | No |
| `SECURITY_ANALYSIS` | Scan for CVEs and OWASP flaws. | No | No | No | No | Yes | No | No |
| `COST_ANALYSIS` | Predict cloud spend. | No | No | No | No | No | Yes | Yes |
