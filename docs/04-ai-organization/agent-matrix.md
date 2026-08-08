# Agent Matrix

This matrix provides a comprehensive cross-reference of every agent role in the ForgeOS AI Organization.

## Comprehensive Agent Matrix

| Agent | Layer | Taxonomy | Can Delegate To | Can Approve | Can Execute Code | Required Tools | Risk Level | Human Approval Required | Typical Outputs |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **CEO Agent** | Executive | Executive | PM, Project Mgr | Strategic decisions | No | None (orchestration only) | LOW | No (escalates to human) | Strategic plans, conflict resolutions |
| **Product Manager** | Management | Manager | BA, Architect | Requirements scope | No | Documentation tools | LOW | No | PRDs, acceptance criteria, roadmaps |
| **Project Manager** | Management | Manager | Eng. Manager, Scrum Master | Task assignments | No | Documentation tools | LOW | No | Schedules, milestones, status reports |
| **Scrum Master** | Management | Manager | None | Process changes | No | Documentation tools | LOW | No | Sprint plans, retrospective reports |
| **Business Analyst** | Management | Advisor | None | Requirement validation | No | Documentation tools | LOW | No | Business rules, workflow maps |
| **Solution Architect** | Engineering | Advisor | None | Architecture decisions | No | File (read), Documentation | MEDIUM | For major ADRs | ADRs, system designs, tech selections |
| **Engineering Manager** | Engineering | Manager | BE, FE, DB, AI/ML | Technical decisions | No | File (read), Documentation | LOW | No | Task breakdowns, technical plans |
| **Frontend Engineer** | Engineering | Specialist | None | No | Yes (Sandbox) | File, Git, Terminal, Build, Test, Browser | MEDIUM | For production deploy | UI components, tests, CSS |
| **Backend Engineer** | Engineering | Specialist | None | No | Yes (Sandbox) | File, Git, Terminal, Build, Test, Database (read) | MEDIUM | For production deploy | APIs, services, unit tests |
| **Database Engineer** | Engineering | Specialist | None | No | Yes (Sandbox) | File, Git, Database, Terminal | HIGH | For migrations | Schemas, migrations, indexes |
| **AI/ML Engineer** | Engineering | Specialist | None | No | Yes (Sandbox) | File, Git, Terminal, AI tools | MEDIUM | For model changes | Embeddings, RAG pipelines, model configs |
| **QA Engineer** | Quality | Reviewer | None | Test pass/fail | Yes (Sandbox, read-heavy) | File (read), Git (read), Test, Terminal | LOW | No | Test plans, test reports, defect logs |
| **Code Review Engineer** | Quality | Reviewer | None | Code review pass/fail | No | File (read), Git (read), GitHub | LOW | No | Review comments, approval/rejection |
| **Security Engineer** | Quality | Reviewer | None | Security pass/fail | No | File (read), Git (read), Cloud (read) | MEDIUM | For critical vulns | Threat models, vulnerability reports |
| **DevOps Engineer** | Operations | Specialist | None | No | Yes (Sandbox) | File, Git, Terminal, Cloud, Build | HIGH | For prod deploy | CI/CD pipelines, Dockerfiles, IaC |
| **SRE Engineer** | Operations | Monitor | None | Incident severity | No | Cloud (read), Terminal (read) | MEDIUM | For prod changes | Monitoring configs, incident reports |
| **Cloud Engineer** | Operations | Specialist | None | No | Yes (Sandbox) | File, Git, Terminal, Cloud | HIGH | For infra changes | Terraform, cloud configs, network rules |
| **Cost Optimization Eng.** | Operations | Advisor | None | No | No | Cloud (read), AI tools (read) | LOW | For cost actions | Cost reports, optimization recommendations |
| **Technical Writer** | Support | Specialist | None | No | No | File, Git, Documentation | LOW | No | READMEs, API docs, changelogs |

## Dynamic Activation Reference

| Project Type | Activated Agents |
| :--- | :--- |
| Simple Static Website | Product Manager, Frontend Engineer, QA Engineer, Technical Writer |
| REST API Service | Product Manager, Architect, Backend Engineer, Database Engineer, QA, Code Reviewer, DevOps, Technical Writer |
| Full-Stack Web App | Product Manager, Project Manager, Architect, Frontend Engineer, Backend Engineer, Database Engineer, QA, Code Reviewer, Security Engineer, DevOps, Technical Writer |
| Enterprise ERP System | CEO, Product Manager, Project Manager, Business Analyst, Architect, Engineering Manager, Frontend, Backend, Database, QA, Code Reviewer, Security, DevOps, SRE, Cloud, Cost Optimization, Technical Writer |
| AI-Powered Application | All of the above + AI/ML Engineer |
