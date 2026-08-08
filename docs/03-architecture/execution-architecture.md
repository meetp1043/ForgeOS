# Execution Sandbox Architecture

The Execution Sandbox is the isolated environment where AI-generated code and tool commands are executed. 

**Warning:** It is fundamentally unsafe to run arbitrary AI-generated code directly on the ForgeOS host machine.

## Isolation Strategies
- **Workspace Isolation**: Each Project has a dedicated, isolated volume or directory structure.
- **Process Isolation**: Commands (e.g., `npm install`, `mvn test`) are executed inside ephemeral Docker containers (or gRPC isolated runtimes).
- **Network Restrictions**: The sandbox operates in a restricted network namespace. It cannot access internal ForgeOS databases or unauthorized internal VPC resources. It can only reach whitelisted external package registries (npm, Maven Central).

## Execution Lifecycle
1. The Orchestrator requests a command execution (e.g., "Run tests").
2. The Sandbox provisions an ephemeral container containing the necessary toolchain (e.g., JDK 21).
3. The Workspace volume is mounted into the container.
4. The command executes.
5. **Resource Limits**: Memory (RAM) and CPU limits are enforced to prevent fork bombs or memory leaks.
6. **Timeouts**: Execution is forcibly killed if it exceeds the SLA.
7. **Logging**: `stdout` and `stderr` are streamed back to the Agent Runtime.
8. **Cleanup**: The ephemeral container is immediately destroyed.
