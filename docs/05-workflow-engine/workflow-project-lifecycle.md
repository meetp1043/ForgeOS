# Workflow: Project Lifecycle

The Project Lifecycle Workflow is the flagship ForgeOS workflow. It orchestrates the complete journey from a user's idea to a deployed, maintained application.

## Phases

### Phase 1: Business Analysis
- **Input**: Raw user idea (natural language).
- **Agent**: Business Analyst.
- **Output**: Structured business requirements, workflows, and acceptance criteria.
- **Approval**: User confirms requirements.
- **Failure Path**: Escalate ambiguity to user for clarification.

### Phase 2: Product Requirements
- **Input**: Business requirements.
- **Agent**: Product Manager.
- **Output**: Product Requirements Document (PRD) with prioritized features.
- **Approval**: User approves PRD.
- **Failure Path**: Requirements conflict → escalate to user.

### Phase 3: Architecture Design
- **Input**: Approved PRD.
- **Agent**: Solution Architect.
- **Output**: System architecture, ADRs, technology decisions.
- **Approval**: User approves architecture.
- **Failure Path**: Infeasible requirement → escalate to Product Manager.

### Phase 4: Project Planning
- **Input**: Approved architecture.
- **Agent**: Project Manager.
- **Output**: Task breakdown, milestones, dependency graph, sprint plan.
- **Approval**: None (informational).
- **Failure Path**: Resource constraint → escalate to user.

### Phase 5: Development (Parallel)
- **Input**: Task assignments.
- **Agents**: Frontend Engineer, Backend Engineer, Database Engineer (parallel fan-out).
- **Output**: Source code, tests, migrations.
- **Approval**: None at this stage.
- **Failure Path**: Build failure → retry → escalate to Engineering Manager.

### Phase 6: Code Review
- **Input**: Completed code artifacts.
- **Agent**: Code Review Engineer.
- **Output**: Review comments, APPROVED/CHANGES_REQUESTED.
- **Approval**: Auto-transition on APPROVED.
- **Failure Path**: CHANGES_REQUESTED → reroute to implementing agent.

### Phase 7: Testing
- **Input**: Reviewed code.
- **Agent**: QA Engineer.
- **Output**: Test results, coverage report.
- **Approval**: None (auto-pass on green).
- **Failure Path**: Test failures → reroute to implementing agent.

### Phase 8: Security Review
- **Input**: Tested code.
- **Agent**: Security Engineer.
- **Output**: Security audit report.
- **Approval**: Security PASS required for deployment.
- **Failure Path**: Vulnerability detected → block deployment, create remediation task.

### Phase 9: Staging Deployment
- **Input**: Security-approved code.
- **Agent**: DevOps Engineer.
- **Output**: Staging environment URL.
- **Approval**: Human smoke test.
- **Failure Path**: Deploy failure → rollback → escalate.

### Phase 10: Production Deployment
- **Input**: Approved staging.
- **Agent**: DevOps Engineer.
- **Output**: Production URL.
- **Approval**: **CRITICAL** — Human must explicitly approve.
- **Failure Path**: Deploy failure → automatic rollback → incident report.

### Phase 11: Observability & Maintenance
- **Input**: Deployed application.
- **Agent**: SRE Engineer.
- **Output**: Monitoring dashboards, health checks.
- **Approval**: None.
- **Failure Path**: Incident → trigger Incident Response workflow.
