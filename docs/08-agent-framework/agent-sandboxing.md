# Agent Sandboxing

Generated code and CLI commands must **never** execute unrestricted on the ForgeOS host operating system.

## Conceptual Sandbox Boundaries

When the Agent Framework executes a tool (like running a unit test or a Python script), it provisions a sandbox with the following boundaries:

- **Filesystem**: Ephemeral workspace. The agent cannot traverse up to `/etc` or read the host's files.
- **Network**: Highly restricted. The sandbox may only have access to the specific internal mock database or API required for the test. Public internet access is disabled by default to prevent data exfiltration.
- **CPU/Memory**: Cgroups limit compute to prevent fork bombs or infinite loops from crashing the ForgeOS node.
- **Process**: Agents cannot see other processes running on the host.
- **Time**: Strict wall-clock limits on container lifespan.
- **Credentials**: Secrets are injected as environment variables *only* into the sandbox at the exact moment of execution, and are never returned in the `stdout` passed back to the LLM.

*(Note: The exact implementation—e.g., Firecracker microVMs, Docker containers, or gVisor—will be defined in the Infrastructure layer, but the Framework architecture assumes this boundary exists).*
