# Runtime Architecture

The Runtime Architecture defines how ForgeOS executes processes over time. It is divided into two primary subsystems:

1. **Orchestration Runtime**: The macro-level execution loop. It manages the project lifecycle, coordinates workflow states, handles distributed messaging via RabbitMQ, and enforces approval gates.
2. **Agent Runtime**: The micro-level execution loop. It manages the specific lifecycle of a single AI agent processing a single task, handling prompt construction, tool execution loops, and LLM API interactions.

*See `orchestration-architecture.md` and `agent-runtime-architecture.md` for detailed designs.*
