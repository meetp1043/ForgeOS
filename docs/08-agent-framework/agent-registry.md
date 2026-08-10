# Agent Registry

The Agent Registry is the authoritative catalog of all Agent Definitions in ForgeOS. 

## Registry Purpose
It acts as the single source of truth for discovering and validating what an agent is allowed to do. The Workflow Engine queries the Registry when attempting to assign a task.

## Registry Contents

The Registry conceptually stores the following for each activated agent:
- `Agent ID`
- `Role`
- `Version` (Immutable tracking)
- `Status` (ACTIVE, DEPRECATED, SUSPENDED, RETIRED)
- `Capabilities` (Array of technical verbs)
- `Permissions` (Array of hard system rights)
- `Tools` (Array of authorized JSON tool schemas)
- `Model Policy`
- `Context Policy`
- `Memory Policy`
- `Risk Level`
- `Parent Role` (Escalation path)
- `Evaluation Status` (Has this version passed adversarial testing?)
- `Availability` (Is this agent online/enabled for this tenant?)
- `Supported Technologies` (e.g., "Java", "Python", "React")

## Mutability
Agent Definitions within the Registry are **immutable per version**. Updating an agent's capability requires registering a new version and deprecating the old one.
