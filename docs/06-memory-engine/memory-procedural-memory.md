# Procedural Memory

Procedural memory stores "how-to" knowledge—the executable instructions, conventions, and operational patterns required to perform tasks effectively within a specific environment.

## Definition
Reusable knowledge about execution strategies, tool utilization, and procedural conventions. 

**Crucial Constraint**: Procedural memory must *never* be allowed to bypass current project policies, security rules, or governance frameworks. It is a guide for execution, not an override for constraints.

## Examples
- "Use Maven wrapper (`./mvnw`) rather than local Maven for this project."
- "Run integration tests locally before triggering a staging deployment."
- "To query the user database in tests, use the `TestDatabaseSeeder` utility."

## Characteristics
- **Owner**: Organization / Agent
- **Scope**: Organization / Project
- **Retention**: Long-term
- **Access**: Available to execution agents (Engineers, QA, DevOps).
- **Typical Retrieval**: Planning phases of agent execution, when an agent is determining *how* to approach a newly assigned task.

## Generation
Formed when agents discover successful patterns (and commit them to memory) or when human users explicitly define standard operating procedures (SOPs) for the AI organization.
