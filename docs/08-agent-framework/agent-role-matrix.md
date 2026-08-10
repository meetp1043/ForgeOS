# Agent Role Matrix

This matrix provides a high-level mapping of standard ForgeOS roles to their operational characteristics.

| Role | Layer | Mission | Responsibilities | Capabilities | Authority | Delegation | Approval | Tools | Context | Memory | Risk | Typical Model |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Product Manager** | Management | Define what to build. | Requirements, Roadmap. | `REQUIREMENT_WRITE`, `AGILE_MGMT` | High (Product) | To BA, Engineers | Human (Epic level) | Jira/Notion APIs | Market data, user feedback | Write Goals | Low | Reasoning (e.g. GPT-4o) |
| **Solution Architect** | Architecture | Define how to build it. | System Design, ADRs. | `ARCHITECTURE_WRITE`, `CODE_READ` | High (Tech) | To Engineers | Human (Major shifts) | Diagramming, DB Analysis | Requirements, existing ADRs | Write Arch | Medium | Reasoning (e.g. GPT-4o) |
| **Backend Engineer** | Engineering | Build the APIs. | Code, Unit Tests. | `CODE_WRITE`, `TEST_EXECUTION` | Low (Implementation only) | None | Arch (for deviations) | IDE, Maven, Git | ADRs, Module code | Write Impl | Medium | Fast (e.g. Claude 3.5 Sonnet) |
| **Database Engineer** | Engineering | Manage data schema. | Migrations, Tuning. | `DATABASE_MIGRATION`, `CODE_WRITE` | Medium (Schema) | None | Arch / Human | Flyway, SQL Client | ADRs, Schema | Write Schema | High | Fast / Reasoning |
| **Code Reviewer** | Quality | Enforce standards. | Static Analysis, PR Approval. | `CODE_REVIEW` | Medium (Gatekeeper) | None | N/A | SonarQube, GitHub | PR Diff, Coding Standards | Read Only | Low | Fast (e.g. Claude 3.5 Sonnet) |
| **Security Engineer** | Quality | Prevent vulnerabilities. | Threat Modeling, SAST/DAST. | `SECURITY_ANALYSIS` | High (Blocker) | None | N/A | SAST scanners | PR Diff, CVE DBs | Write Sec | Medium | Reasoning (e.g. GPT-4o) |
| **DevOps Agent** | Operations | Ship the code. | CI/CD, Infrastructure. | `DEPLOYMENT`, `INFRA_WRITE` | Medium (Pipeline) | None | Human (Production) | Terraform, AWS CLI, Docker | Artifacts, Runbooks | Read Only | Critical | Fast (e.g. GPT-4o-mini) |
