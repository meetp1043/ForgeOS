# Future Evolution

As LLMs improve, the Agent Framework will evolve to offload more orchestration to the models themselves, while tightening security boundaries.

## Roadmap Concepts

- **Dynamic Capability Inference**: Instead of a human defining `CODE_WRITE` in the Registry, a future Framework might dynamically test a new foundation model in a sandbox to determine what capabilities it naturally possesses, auto-generating the Agent Definition.
- **Streaming Tool Execution**: Moving away from the discrete `Plan -> Request Tool -> Wait -> Receive Output` loop to a streaming model where the LLM interacts with a terminal in real-time, reducing latency.
- **Multi-Agent Shared Context**: Allowing multiple agents to collaborate in a single shared LLM context window (like a group chat), reducing the token overhead of passing artifacts back and forth through the Workflow Engine.
- **On-Device Agents**: Extending the Framework to support lightweight agents running directly on developers' laptops for hyper-local tasks, coordinating with the central ForgeOS cloud.
