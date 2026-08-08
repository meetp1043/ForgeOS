# Tool System Architecture

Tools are the hands of the AI agents. Without tools, agents can only talk; with tools, they can affect the real world.

## Tool Execution Boundaries
Agents must NEVER receive unrestricted system access to the ForgeOS host machine by default. All tool executions that interact with the filesystem, compilers, or shell environments must occur within the isolated `Execution Sandbox`.

## Tool Concepts
- **Tool Definition**: A declarative schema (e.g., JSON Schema) defining the tool's inputs, outputs, and purpose.
- **Tool Permission**: Granular RBAC ensuring an agent can only access tools mapped to its role.
- **Tool Invocation**: The API boundary where an agent requests a tool execution.
- **Tool Result**: The structured or unstructured output returned to the agent's context.

## Example Tools
- `filesystem`: read, write, list directories.
- `Git` / `GitHub`: commit, branch, open PR.
- `terminal`: execute bash commands (inside the sandbox).
- `compilers`: Maven, Gradle, npm (inside the sandbox).
- `cloud APIs`: AWS/GCP SDK operations.

## Security Controls
- **Timeout**: Hard limits on execution time (e.g., a Maven build cannot hang for 3 hours).
- **Retry**: Idempotent tools can be retried automatically on network failure.
- **Audit**: Every single tool invocation is logged to the `observability` module with the Agent ID and prompt that triggered it.
