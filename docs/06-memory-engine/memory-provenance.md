# Memory Provenance

Every important memory in ForgeOS must answer a critical question: **Where did this information come from?**

Without provenance, AI systems become "black boxes" of hallucinatory facts. Provenance ensures accountability, traceability, and aids in conflict resolution.

## Source References
A memory entry's `Provenance` attribute must link to the original source. 

### Examples of Provenance Sources:
- **User**: The exact `UserID` and `ChatID` where the user stated a preference.
- **Product Manager Agent**: The `AgentID` and `TaskID` where the PRD was formulated.
- **Architect Agent**: The workflow step that produced a specific technical decision.
- **Repository**: The specific commit hash and file path where a configuration was detected.
- **Document**: An internal wiki page or uploaded PDF (with `ArtifactID`).
- **Tool**: The `ToolRunID` of a compiler or test runner that proved a procedure works.
- **External Integration**: Data fetched from Jira, GitHub, or AWS.

## Chain of Custody
Provenance is not just a pointer; it is a chain of custody. If Agent A creates a hypothesis, and Human B approves it, the provenance must reflect:
`[GeneratedBy: Agent A (Task X)] -> [ApprovedBy: Human B (Event Y)]`

When an agent retrieves a memory, the provenance metadata is included in the context assembly, allowing the agent to cite its sources when communicating with the user.
