# Agent Risk Classification

Every Agent Definition and Task carries a Risk Classification. The classification dictates the level of approval, monitoring, and sandboxing required.

## Risk Levels

### LOW
- **Definition**: Actions that are read-only or only affect non-executable documentation.
- **Examples**: Writing a README, summarizing a meeting, analyzing code without saving changes.
- **Controls**: May execute autonomously without human review.

### MEDIUM
- **Definition**: Standard development actions isolated to non-production environments.
- **Examples**: Writing source code on a feature branch, running local unit tests, merging low-impact PRs.
- **Controls**: May execute autonomously but requires peer AI review (Code Review Agent) or standard CI pipeline validation.

### HIGH
- **Definition**: Actions that mutate shared environments or critical data schemas.
- **Examples**: Database migrations in staging, merging to `main`, deploying to test environments, altering IAM roles.
- **Controls**: Requires explicit approval from a higher-level Manager Agent or a Human operator.

### CRITICAL
- **Definition**: Actions that can cause unrecoverable data loss, production outages, or severe financial cost.
- **Examples**: Production deployments, dropping database tables, deleting cloud infrastructure, modifying live credentials.
- **Controls**: **Requires explicit, auditable Human authorization.** No AI agent may unilaterally approve a CRITICAL action.
