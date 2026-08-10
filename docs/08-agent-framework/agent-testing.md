# Agent Framework Testing

The Agent Framework itself must be rigorously tested to ensure it correctly sandboxes and manages agents. This testing applies to the Java/Spring Boot code implementing the framework, not just the agents.

## Required Test Categories

- **Unit Tests**: Verify that isolated classes (e.g., `ToolValidator`, `TokenBudgetCalculator`) function correctly.
- **Integration Tests**: Verify the Framework correctly communicates with the Workflow Engine and Context Engine.
- **Contract Tests**: Verify the JSON payload schemas between the Framework and external LLM providers (e.g., ensuring OpenAI function calling schemas are built correctly).
- **Agent Behavior Tests**: Instantiate a mock agent with a predefined prompt and assert that it emits the expected structured `Result`.
- **Prompt Tests**: Verify that the Framework correctly concatenates System, Policy, and Task prompts in the correct hierarchical order without truncation errors.
- **Permission Tests**: Assert that an agent lacking `DATABASE_WRITE` is correctly blocked by the Framework when it attempts to invoke the `execute_sql` tool.
- **Tool Tests**: Verify that the Sandbox execution environment successfully runs and captures the output of authorized tools.
- **Security Tests**: Verify that injected secrets are never written to the standard audit log.
- **Workflow Tests**: Verify the lifecycle transitions (`ASSIGNED` -> `COMPLETED`).
- **Evaluation Datasets**: Maintain a static suite of 1,000 diverse tasks to benchmark new agent versions.
- **Failure Tests**: Force network timeouts to the LLM provider and assert the Framework correctly enters the Retry loop.
- **Cost Tests**: Mock a high-token response and assert the `BUDGET_EXCEEDED` circuit breaker trips correctly.
