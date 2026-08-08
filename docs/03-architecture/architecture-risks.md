# Architecture Risks

| Risk | Impact | Likelihood | Mitigation | Residual Risk |
| :--- | :--- | :--- | :--- | :--- |
| **Agent Complexity** | Agents get stuck in infinite logic loops talking to each other. | High | Strict hierarchical orchestration; absolute token/retry limits. | Medium |
| **Sandbox Escape** | Agent-generated bash script escapes the Docker sandbox and accesses the host. | Medium | Rootless containers, dropped capabilities, restricted network namespaces. | Low |
| **Context Explosion** | Project history grows so large it exceeds the LLM context window. | High | Aggressive RAG summarization; sliding context windows. | Medium |
| **Vendor Lock-in** | OpenAI changes pricing or deprecates a model, breaking ForgeOS. | Medium | `Spring AI` Model Router allowing instant fallback to Anthropic/Gemini. | Low |
| **Memory Corruption** | Vector database retrieves the wrong context, causing the agent to delete code. | Medium | Human-in-the-loop approval gates for destructive actions. | Low |
| **Distributed Complexity** | Microservices fail silently, making debugging impossible. | Low | **Spring Modulith** approach prevents premature distributed computing. | Low |
| **Multi-Tenancy Isolation** | Tenant A accesses Tenant B's source code. | High | Strict RLS in Postgres and segregated Docker volumes per workspace. | Low |
| **Observability Overhead** | Logging every single LLM token and tool result overwhelms the database. | Medium | Sampling for successful runs; strict retention policies; async logging. | Medium |
