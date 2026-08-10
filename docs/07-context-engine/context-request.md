# Context Request

The Context Request is the formal payload submitted to the Context Engine to initiate the assembly of a Context Package.

## Structure of a Context Request

Conceptually, a request must define *who* is asking, *where* they are operating, and *what* they are trying to achieve.

### Required Fields
- **RequestID**: Unique identifier for traceability and caching.
- **TenantID**: Hard boundary for data isolation.
- **ProjectID**: Primary boundary for artifact and memory isolation.
- **TaskID**: The specific unit of work being executed.
- **AgentRole**: The persona of the agent (e.g., `BACKEND_ENGINEER`, `SECURITY_AUDITOR`), which dictates the default permission set.
- **Objective**: A natural language description of the goal (e.g., "Implement the POST /users endpoint").

### Optional/Dynamic Fields
- **OrganizationID**: Used if querying cross-project Tenant-level policies.
- **WorkspaceID / RepositoryID**: Narrows the scope of source code retrieval.
- **WorkflowID**: Provides broader state awareness (e.g., "We are currently in the Testing phase").
- **RequestedSources**: Explicit directives (e.g., `["ADR-004", "auth-service/src/"]`).
- **RiskLevel**: Informs whether the Context Engine should "Fail Closed" if security context is unavailable.
- **TokenBudget**: The maximum allowable tokens for the final package.
- **FreshnessRequirement**: E.g., `REALTIME` for incident response, `CACHED` for general code writing.
- **Deadline**: The timeout limit for the Context Engine to respond before the agent runtime fails.
