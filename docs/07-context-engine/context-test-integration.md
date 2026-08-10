# Test Integration

Test context is crucial for verifying that an agent's implementation meets the established behavioral requirements and does not introduce regressions.

## Relevant Test Elements
When compiling context for a development or QA task, the engine may include:
- **Existing Tests**: The current unit, integration, or e2e tests covering the target module.
- **Failed Tests**: The specific test cases currently failing (critical for bug-fixing tasks).
- **Test Reports**: Summaries of recent CI/CD pipeline runs.
- **Coverage**: Lines/branches currently lacking test coverage.
- **Acceptance Criteria**: The BDD/TDD requirements mapped to tests.
- **Test Conventions**: Project-specific rules (e.g., "Use JUnit 5 and AssertJ").
- **Previous Failures**: Historical flakiness or known issues with the test suite.

## Integration Strategy
To save tokens, if a test suite has 50 passing tests and 2 failing tests, the Context Engine will prioritize injecting the exact code of the 2 failing tests, and may only provide the names/signatures of the 50 passing tests.
