# Failure Memory

Failure Memory is how ForgeOS learns operationally from mistakes, ensuring that the system does not endlessly repeat the same errors.

## Definition
Specific, cataloged instances of system, tool, or code failures, paired with the context of *why* it failed and (if applicable) *how* it was resolved.

## Examples
- **Dependency Issue**: "Updating `react-router` to v6 broke the navigation component in this specific repo structure."
- **Deployment Failure**: "AWS Lambda deployment failed because the deployment package exceeded 250MB."
- **Tool Failure**: "The `search_web` tool fails when querying internal intranet sites."

## The "Validation" Constraint
Failure memory must not automatically become an unquestioned, permanent rule.
- If a deployment failed due to a temporary AWS outage, the agent should not permanently refuse to deploy to AWS.
- Failure memory must be validated and contextualized. It serves as a *warning* to execution agents ("Check the package size before deploying"), not a strict block, unless explicitly promoted to a Procedural Memory by a human.

## Characteristics
- **Owner**: Organization / Agent
- **Scope**: Task / Project / Organization (depending on applicability).
- **Retention**: Medium to Long-term.
- **Access**: Execution and planning agents.
- **Typical Retrieval**: Injected into context when an agent is planning a high-risk action, or when an agent encounters a similar error stack trace.
