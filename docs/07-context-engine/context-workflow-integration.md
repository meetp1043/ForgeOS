# Workflow Integration

Context depends heavily on workflow state. An agent's informational needs shift drastically depending on whether they are designing, coding, testing, or deploying.

## Context by Workflow Phase

### Requirements Phase
- **Primary Context**: Business context, user requests, product specs.
- **Excluded**: Low-level source code (unless needed for feasibility checks).

### Architecture Phase
- **Primary Context**: Requirements, project constraints, existing ADRs, technology stack.

### Development Phase
- **Primary Context**: The specific task description, approved architecture constraints, relevant module source code, and associated tests.

### Testing Phase
- **Primary Context**: The implemented code, acceptance criteria, historical test conventions, and previous test failures.

### Deployment Phase
- **Primary Context**: Build artifacts, environment configuration, infrastructure-as-code, security policies, and release approvals.

### Incident Phase
- **Primary Context**: Production logs, metrics, recent deployment changes, known previous incidents, and runbooks. (This phase prioritizes **freshness** above all else).
