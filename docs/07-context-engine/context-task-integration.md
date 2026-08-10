# Task Integration

The Task is the nucleus of the Context Package. It defines exactly what the agent is supposed to do *right now*.

## Task Context Elements
Task context must explicitly include:
- **Task Objective**: A clear, concise statement of the goal.
- **Description**: Detailed requirements for the specific unit of work.
- **Acceptance Criteria**: The exact conditions under which the task is considered complete.
- **Dependencies**: Other tasks or systems this task relies upon.
- **Assigned Agent**: (e.g., "You are executing this as the Backend Engineer").
- **Previous Attempts**: If the task was attempted and failed, why?
- **Known Failures**: Explicit compiler errors or test failures tied to this task ID.
- **Related Artifacts**: Specific files or documents linked to the ticket (e.g., a Figma design link).

## Priority
Task context is **CRITICAL**. It can never be silently dropped or aggressively compressed in a way that alters the objective. If the task description itself exceeds the token budget, the task must be split by the Workflow Engine.
