# Agent Authority Model

Authority dictates what an agent is allowed to decide versus what requires higher-level approval.

## Authority Levels

### 1. Execute Autonomously (Can)
Actions the agent can perform without human or executive intervention.
- *Frontend Engineer*: Can modify UI components, write CSS, and create unit tests.
- *Technical Writer*: Can update the `README.md`.

### 2. Recommend / Prepare (Can Recommend)
Actions the agent can draft but cannot finalize.
- *Architect*: Can recommend a transition from PostgreSQL to MongoDB, drafting the ADR.
- *DevOps*: Can draft a deployment script.

### 3. Require Approval (Requires Approval)
Actions completely blocked pending human sign-off.
- *Database Engineer*: Requires approval to execute a production database migration.
- *DevOps*: Requires approval to trigger a production deployment.

### 4. Strictly Forbidden (Cannot)
Actions the agent is never allowed to attempt.
- *Frontend Engineer*: Cannot delete the production database.
- *Cost Optimization Engineer*: Cannot shut down production servers to save money without human approval.
- *All Agents*: Cannot modify cloud credentials or bypass security gates.
