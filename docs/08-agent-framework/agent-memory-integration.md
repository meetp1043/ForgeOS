# Memory Integration

The Agent Framework interacts with the Memory Engine primarily for writing new facts and decisions discovered during execution. (Reading memory is handled upstream by the Context Engine).

## Integration Flow

1. **Discovery**: During execution, the agent makes a structural decision (e.g., "I had to downgrade the library to v2.1 due to a transitive conflict").
2. **Extraction**: The agent includes this finding in its final `Result` payload under the `recommendations` or `architectural_decisions` fields.
3. **Validation**: The Framework verifies the agent's `Memory Policy` permits it to write this type of knowledge.
4. **Publishing**: The Framework forwards the payload to the Memory Engine.
5. **Persistence**: The Memory Engine vectorizes and stores the fact for future agents to retrieve.

## Boundary Enforcement
Agents cannot bypass the Memory Engine's internal authorization policies. A Junior Backend Engineer cannot overwrite an established Memory fact authored by the Principal Architect.
