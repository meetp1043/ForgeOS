# Architecture Overview

ForgeOS is an AI Software Engineering Operating System designed to orchestrate specialized AI agents. 

## High-Level Architecture Flow

The conceptual architecture follows a layered approach from the user interface down to the execution of AI models and tools:

```mermaid
flowchart TD
    User([User]) --> WebApp[Web Application (Next.js/React)]
    WebApp --> API[API Layer (Spring Boot / REST)]
    
    subgraph ForgeOS Core (Spring Modulith)
        API --> Orchestration[Orchestration Layer]
        Orchestration --> AgentRuntime[Agent Runtime]
        Orchestration --> Workflow[Workflow Engine]
        
        AgentRuntime --> Memory[Memory Engine]
        AgentRuntime --> Context[Context Engine]
        AgentRuntime --> ModelRouter[Model Router]
        AgentRuntime --> ToolRegistry[Tool Registry]
    end
    
    ModelRouter --> AIProviders[(AI Providers: OpenAI, BYOK, Ollama)]
    ToolRegistry --> Tools[Execution Sandbox / external tools]
    Tools --> External[External Systems: GitHub, AWS, etc.]
```

## Responsibilities and Boundaries

- **Web Application**: The Next.js frontend responsible for conversational UX, dashboards, and visualizations.
- **API Layer**: Spring Boot REST controllers exposing functionality and securing endpoints.
- **Orchestration Layer**: Manages the overarching Project lifecycle, breaking objectives into Tasks, and coordinating Agent interactions.
- **Workflow & Task Engines**: Durable state machines for tracking long-running tasks, approvals, and dependencies.
- **Agent Runtime**: The execution environment for individual Agents. Manages their lifecycle, state, and permissions.
- **Memory Engine**: Handles persistence and retrieval of project history and decisions (vector/graph storage).
- **Context Engine**: Assembles the exact state and relevant files an Agent needs in its prompt.
- **Model Router**: Abstracts the LLM provider, handling failover, cost optimization, and routing between cloud and local AI.
- **Execution Sandbox**: The secure, isolated boundary where generated code is actually executed or analyzed.
