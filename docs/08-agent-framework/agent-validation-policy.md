# Agent Validation Policy

An agent is strictly prohibited from claiming a task is `COMPLETED` without providing mandatory evidence. The Validation Policy acts as the internal QA for the agent itself.

## Required Validation by Role

### Backend Agent
- **Requirement**: Code must compile.
- **Requirement**: Unit tests covering new logic must pass.
- **Requirement**: No syntax errors in changed files.

### Frontend Agent
- **Requirement**: Build process (e.g., Webpack/Vite) must succeed.
- **Requirement**: UI component tests must pass.

### Database Agent
- **Requirement**: Migration scripts must run successfully against an ephemeral test database.
- **Requirement**: Schema consistency checks must pass.

### QA Agent
- **Requirement**: Must output a structured test report.
- **Requirement**: Must explicitly link failures to requirements.

### DevOps / SRE Agent
- **Requirement**: Must provide the unique deployment ID or health check URL.
- **Requirement**: Must verify environment stability post-deployment.

If an agent submits a Result Payload marked `SUCCESS` but fails to provide the required evidence (e.g., no test output), the Agent Framework will automatically reject the result and transition the agent to `FAILED` or `RETRY`.
