# Agent Tools

Tools are the interfaces through which an agent interacts with the world outside its prompt. The Agent Framework strictly controls which tools an agent instance receives.

## Role-Based Tool Access
Agents must receive only the tools required for their role and immediate task. Providing a Frontend Engineer agent with the `Kubernetes CLI` tool expands its attack surface and distracts the LLM with unnecessary options.

## Potential Tools Registry
- **Filesystem**: Read/Write/Delete/Search files.
- **Git**: Branch, Commit, Push, Diff.
- **GitHub**: Create PRs, review issues.
- **Terminal**: Execute bash/powershell commands (Highly sandboxed).
- **Build Tools**: Maven, Gradle, npm.
- **Containers**: Docker build/run.
- **Database**: Execute SQL queries.
- **Browser**: Playwright/Puppeteer for UI testing.
- **Cloud**: AWS CLI or Terraform integration.
- **Kubernetes**: `kubectl` integration.
- **Testing**: JUnit/Jest execution wrappers.
- **Static Analysis**: SonarQube or ESLint runners.
- **Documentation**: Swagger/OpenAPI generators.
- **AI Model**: Ability to invoke smaller sub-models for tasks like summarization.

## Tool Schema
Tools are injected into the agent's context as JSON Schemas (e.g., OpenAI Function Calling). The Framework strips unauthorized tools from the schema before sending the payload to the Model Router.
