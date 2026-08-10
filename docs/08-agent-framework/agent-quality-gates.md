# Agent Quality Gates

Quality gates are the role-specific criteria that an agent's `Result` must pass before the Workflow Engine accepts the task as complete.

## Role-Specific Gates

### Backend Agent
- **Build**: `mvn clean compile` must return `0`.
- **Tests**: `mvn test` must return `0`.
- **Review**: Must be approved by a Code Review Agent.
- **Security**: Must pass SAST scanning with 0 Critical/High findings.

### Frontend Agent
- **Build**: `npm run build` must succeed.
- **Tests**: Component/Unit tests must pass.
- **Review**: Must be approved by a Code Review Agent.
- **UI Validation**: (Where applicable) Playwright visual tests must pass.

### Database Agent
- **Migration Validation**: The generated `.sql` file must execute successfully against an ephemeral test container (e.g., Testcontainers).
- **Data Integrity**: Foreign keys and constraints must remain valid.
- **Performance**: Cannot introduce unindexed cross-joins on massive tables.
- **Review**: Requires Human DBA or Lead Architect approval for destructive schema changes.

### QA Agent
- **Execution**: Must provide a structured report of all tests run.
- **Results**: Must clearly indicate pass/fail rates.
- **Coverage**: Must prove coverage increased or remained stable (where appropriate).

### Security Agent
- **Findings**: Must output a structured SARIF or JSON vulnerability report.
- **Severity**: Must classify all findings correctly.
- **Evidence**: Must provide the snippet of code containing the flaw.

### DevOps / SRE Agent
- **Deployment**: Must return the successful CI/CD run ID.
- **Health**: Must provide an HTTP 200 health check response from the newly deployed service.
- **Rollback**: Must provide evidence that a rollback plan is documented and ready.
- **Incident Readiness**: Must link the deployment to relevant monitoring dashboards.
