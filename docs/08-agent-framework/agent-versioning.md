# Agent Versioning

Agent Definitions are version-controlled entities, much like software packages.

## The Immutability Rule
Once an Agent Version (e.g., `backend-engineer:v2.1.0`) is activated and used in an execution, it becomes **immutable**. 

If the organization updates the Backend Engineer's prompt to include a new Java standard, it must be saved as a new version (e.g., `v2.2.0`).

## Reproducibility
Historical executions must remain reproducible (as far as practical, given LLM non-determinism). If a bug is discovered in production, engineers must be able to audit the specific `Agent Execution ID`, look up the exact `Agent Version ID` used, and see the exact prompts, tools, and permissions that were active at that exact moment in time.

## Version Components
A semantic version change is triggered by modifying any of the following in the Agent Definition:
- Role or Responsibilities
- Capabilities or Permissions
- Tools array
- Model or Context Policies
- System instructions or prompts
- Output/Validation criteria
