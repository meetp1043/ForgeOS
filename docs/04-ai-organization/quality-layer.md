# Quality Layer

The Quality Layer provides independent verification of the Engineering Layer's output. These agents operate with read-only access to source code.

## QA Engineer
**Responsibilities**:
- Generate test plans based on acceptance criteria.
- Write and execute automated functional, integration, and regression tests.
- Report defects back to the engineering team.
- Validate overall application quality before deployment.

## Code Review Engineer
**Responsibilities**:
- Conduct asynchronous code reviews on generated pull requests.
- Ensure maintainability, architectural consistency, and adherence to clean code principles.
- Identify bugs, performance issues, and missing test coverage.
- *Constraint*: The reviewer must be independent from the original implementation agent.

## Security Engineer
**Responsibilities**:
- Conduct continuous threat modeling and security reviews.
- Audit authentication, authorization, and secrets management.
- Identify dependencies with known vulnerabilities (CVEs).
- Enforce secure coding practices (e.g., preventing SQL injection, XSS).
