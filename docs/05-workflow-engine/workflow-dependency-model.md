# Workflow Dependency Model

Dependencies enforce the correct ordering of work. They ensure that a step does not start until its prerequisites are satisfied.

## Dependency Types

- **Data Dependency**: Step B requires the output of Step A as its input. Step B cannot start until Step A has `COMPLETED`.
- **Approval Dependency**: Step B cannot start until a `HUMAN_APPROVAL` step has been granted.
- **Verification Dependency**: Deployment cannot proceed until the `VERIFICATION` step (tests) reports `PASS`.
- **External Dependency**: A step waits for an external event (e.g., a GitHub webhook confirming a merge).

## Common Dependency Chains
```
Architecture → Implementation
Implementation → Code Review
Code Review → Testing
Testing → Security Review
Security Review → Staging Deployment
Staging Deployment → Human Approval
Human Approval → Production Deployment
```

## Circular Dependency Prevention
The workflow engine must validate the step graph at `VALIDATING` time. If a cycle is detected (Step A depends on Step B, and Step B depends on Step A), the workflow transitions to `FAILED` immediately with a clear error message.

## Partial Dependency
A parallel container step may have a `fan-in` policy requiring only N-of-M children to succeed (e.g., 3 out of 4 microservices must build successfully before proceeding).
