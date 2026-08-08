# Agent Communication Model

Agents must NOT communicate through unstructured, infinite chat rooms. This leads to hallucinations, infinite loops, and context degradation.

## Structured Messaging
All inter-agent communication occurs via structured events managed by the Orchestrator.

### Message Types
- **Agent Request**: Parent asks Child to do something.
- **Agent Response**: Child replies to Parent.
- **Task Assignment**: The formal binding of work.
- **Task Result**: The formal return of work (contains paths to modified files).
- **Review Request**: Specialist asks Reviewer to audit code.
- **Review Result**: Reviewer returns PASS/FAIL with specific feedback.
- **Escalation**: Agent explicitly states it is stuck and needs human or parent intervention.
- **Approval Request**: Agent pauses and requests human sign-off.
- **Failure Report**: The system detects an unhandled exception and reports it.

## The Artifact Hand-off
Agents rarely send large blocks of text to each other. Instead, they produce **Artifacts** (e.g., `schema.sql`, `architecture.md`). 
When Agent A messages Agent B, it sends a pointer: *"I have completed the backend implementation. Please review artifact ID 12345."*
