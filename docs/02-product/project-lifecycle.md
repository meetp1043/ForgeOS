# Project Lifecycle

The ForgeOS project lifecycle represents the standard flow of a software project.

### 1. DISCOVERY
- **Entry Conditions**: User inputs a high-level idea.
- **Responsible Agents**: Business Analyst.
- **Artifacts**: Raw context notes, user goals.
- **Exit Conditions**: Sufficient context gathered to write requirements.

### 2. ANALYSIS & 3. REQUIREMENTS
- **Entry Conditions**: Discovery complete.
- **Responsible Agents**: Product Manager, Business Analyst.
- **Artifacts**: Product Requirements Document (PRD).
- **Approval Gates**: User must approve the PRD.
- **Failure Handling**: If rejected, agent rewrites PRD based on user feedback.

### 4. ARCHITECTURE
- **Entry Conditions**: PRD approved.
- **Responsible Agents**: Solution Architect.
- **Artifacts**: System Design, Architecture Decision Records (ADRs).
- **Approval Gates**: User must approve the architecture.

### 5. PLANNING
- **Entry Conditions**: Architecture approved.
- **Responsible Agents**: Project Manager.
- **Artifacts**: Task list (Sprint backlog).
- **Approval Gates**: User can optionally review the task breakdown.

### 6. DEVELOPMENT & 7. TESTING
- **Entry Conditions**: Tasks assigned to developers.
- **Responsible Agents**: Backend/Frontend Developers, QA Engineer.
- **Artifacts**: Source code, Unit/Integration tests.
- **Failure Handling**: If tests fail, task routes back to the developer in an autonomous retry loop.

### 8. REVIEW & 9. SECURITY
- **Entry Conditions**: Code written and tests passing.
- **Responsible Agents**: Code Reviewer, Security Reviewer.
- **Artifacts**: Code review reports, static analysis logs.
- **Approval Gates**: AI Reviewer approves. User must explicitly approve the final merge to main.

### 10. RELEASE & 11. DEPLOYMENT
- **Entry Conditions**: Code merged to main.
- **Responsible Agents**: DevOps Engineer.
- **Artifacts**: Dockerfiles, Deployment manifests.
- **Approval Gates**: User must explicitly click "Deploy".
- **Failure Handling**: If deployment fails, rollback is triggered and SRE agent is notified.

### 12. OBSERVABILITY & 13. MAINTENANCE
- **Entry Conditions**: Application running in production.
- **Responsible Agents**: SRE, Maintenance Engineer.
- **Artifacts**: Incident reports, dependency bump PRs.
- **Exit Conditions**: Project is archived or paused.
