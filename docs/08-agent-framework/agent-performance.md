# Agent Performance

Agent performance impacts the perceived responsiveness of ForgeOS.

## Performance Measurement Points

The Framework measures latency across distinct phases of the execution lifecycle:

- **Queue Time**: Time spent in `ASSIGNED` before moving to `INITIALIZING`. (Indicates system load/worker starvation).
- **Startup Time**: Time to provision the Instance and validate permissions.
- **Context Preparation**: Time spent waiting for the Context Engine.
- **Model Latency**: Time-to-First-Token (TTFT) and total generation time from the LLM provider.
- **Tool Latency**: Time spent inside the Sandbox executing external commands (e.g., waiting for `npm install`).
- **Memory Retrieval**: Time spent writing/reading from the Memory Engine.
- **Result Validation**: Time spent checking the final evidence against the Validation Policy.
- **Total Execution**: The sum of all active phases.

To improve performance, the Framework should parallelize tool execution where safe, and utilize model routing to send simpler tasks to faster, lower-latency models.
