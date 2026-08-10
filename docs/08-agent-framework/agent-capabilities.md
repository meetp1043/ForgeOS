# Agent Capabilities

Capabilities are granular, composable skills that an agent possesses. Unlike a Role (which is abstract), a Capability maps directly to the technical ability to perform a category of actions.

## Capability Design
Capabilities should be atomic and independent of specific tools. 

### Examples of Capabilities
- `CODE_READ`: Ability to understand and parse source code.
- `CODE_WRITE`: Ability to generate new source code.
- `CODE_REFACTOR`: Ability to restructure existing code safely.
- `CODE_REVIEW`: Ability to critique code based on standards.
- `TEST_EXECUTION`: Ability to run test suites.
- `API_DESIGN`: Ability to author OpenAPI/GraphQL contracts.
- `DATABASE_ANALYSIS`: Ability to analyze schemas and slow queries.
- `DATABASE_MIGRATION`: Ability to write DDL scripts.
- `ARCHITECTURE_ANALYSIS`: Ability to map system topologies.
- `DOCUMENTATION`: Ability to write technical prose.
- `DEPLOYMENT`: Ability to push artifacts to environments.
- `INCIDENT_ANALYSIS`: Ability to parse Datadog/PagerDuty logs.
- `AI_MODEL_EVALUATION`: Ability to judge another LLM's output.
- `CLOUD_ANALYSIS`: Ability to read AWS/GCP state.
- `COST_ANALYSIS`: Ability to calculate infrastructure spend.

## Usage in Selection
The Agent Framework uses capabilities during `Agent Selection`. If a task requires `DATABASE_MIGRATION` and `CODE_WRITE`, the framework will search the Agent Registry for an agent Role that possesses both capabilities (e.g., the Database Engineer).
