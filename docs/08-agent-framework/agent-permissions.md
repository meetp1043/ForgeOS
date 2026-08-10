# Agent Permissions

Permissions are hard technical constraints enforced by the Agent Framework. They dictate exactly which operations an agent's tools are allowed to execute. 

## The Principle of Least Privilege
No agent receives universal admin access. Agents are granted only the minimum permissions required to fulfill their specific task.

## Conceptual Permission List

### Filesystem
- `FILE_READ`: Low Risk.
- `FILE_WRITE`: Medium Risk.
- `FILE_DELETE`: High Risk.

### Version Control
- `GIT_READ`: Low Risk.
- `GIT_BRANCH`: Low Risk.
- `GIT_COMMIT`: Medium Risk.
- `GIT_PUSH`: High Risk.
- `GITHUB_READ`: Low Risk.
- `GITHUB_WRITE`: Medium Risk.
- `CREATE_PULL_REQUEST`: Medium Risk.
- `MERGE_PULL_REQUEST`: High Risk (often requires approvals).

### Database
- `DATABASE_READ`: Medium Risk (depends on PII).
- `DATABASE_WRITE`: High Risk.
- `DATABASE_MIGRATION`: Critical Risk (schema changes).

### Infrastructure
- `CLOUD_READ`: Low Risk.
- `CLOUD_WRITE`: High Risk.
- `INFRASTRUCTURE_DELETE`: Critical Risk.

### Deployment
- `DEPLOY_DEV`: Low Risk.
- `DEPLOY_TEST`: Medium Risk.
- `DEPLOY_STAGING`: High Risk.
- `DEPLOY_PRODUCTION`: Critical Risk.

### Security
- `SECRET_REFERENCE`: High Risk (Ability to request the injection of a secret).
- `SECRET_MANAGEMENT`: Critical Risk (Ability to rotate or view raw secrets).
- `DATA_DELETE`: Critical Risk.

## Enforcement
Permissions are cryptographically or programmatically enforced by the Tool System Sandbox, not merely requested politely in the agent's prompt.
