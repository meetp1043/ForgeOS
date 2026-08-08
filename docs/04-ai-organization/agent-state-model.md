# Agent State Model

The state of an agent is tracked durably in the Orchestrator's relational database.

## State Machine
```mermaid
stateDiagram-v2
    [*] --> Created
    Created --> Assigned
    Assigned --> Initializing
    Initializing --> Executing
    Executing --> Waiting : Calls async tool
    Waiting --> Executing : Tool returns
    Executing --> AwaitingApproval : Hits policy gate
    AwaitingApproval --> Executing : Human approves
    AwaitingApproval --> Failed : Human rejects
    Executing --> Completed : Success
    Executing --> Escalated : Needs help
    Escalated --> Executing : Issue resolved
    Executing --> Failed : Out of tokens/retries
    Completed --> [*]
    Failed --> [*]
```

## Durable Transitions
State transitions must be committed to the database *before* taking action, allowing the system to recover gracefully if the ForgeOS server crashes during `Executing`.
