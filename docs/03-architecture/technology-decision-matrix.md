# Technology Decision Matrix

This matrix justifies the core technology selections for the ForgeOS engine.

| Technology | Purpose | Advantages | Disadvantages | Initial Decision | Future Reconsideration Criteria |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Spring Boot 3.x** | Core API Framework | Massive ecosystem, enterprise stability, developer familiarity. | Heavier memory footprint than Go/Rust. | **Adopt** | If startup times (even with AOT/GraalVM) become a bottleneck for ephemeral workloads. |
| **Spring Modulith** | Architecture Enforcement | Prevents spaghetti code in a monolith; allows easy extraction later. | Requires strict discipline to avoid cyclic dependencies. | **Adopt** | If inter-module events become too complex, forcing extraction to microservices. |
| **Spring AI** | LLM Abstraction | Standardized API for OpenAI, Ollama, Vertex, etc. | Newer framework; might lack bleeding-edge provider features. | **Adopt** | If it fails to support advanced multimodality or streaming features we require. |
| **PostgreSQL** | Primary Database | ACID compliance, JSONB support, pgvector for RAG. | Vertical scaling limits. | **Adopt** | If write-throughput for agent logs exceeds Postgres limits. |
| **MongoDB** | Unstructured Store | Flexible schemas for dynamic agent artifacts. | Eventual consistency; operational overhead if running alongside Postgres. | **Evaluate** | If Postgres JSONB proves insufficient for artifact storage. |
| **Redis** | Cache & Locks | Extremely fast, distributed locks, session management. | In-memory only; data loss risk if not persisted. | **Adopt** | N/A (Standard industry practice). |
| **RabbitMQ** | Message Broker | Durable queues for workflow engine events. | Harder to scale horizontally than Kafka. | **Adopt** | Switch to Kafka if event throughput exceeds 10k/sec. |
| **Next.js** | Frontend Framework | React ecosystem, SSR for SEO, great developer experience. | Overkill for purely static dashboards. | **Adopt** | N/A (Standard industry practice). |
| **Docker** | Execution Sandbox | Ubiquitous containerization for isolated agent tool execution. | Docker-in-Docker (DinD) security risks. | **Adopt** | Move to Firecracker microVMs or gRPC isolates if security audits fail. |
| **AWS** | Primary Cloud | Mature ecosystem (ECS, RDS, S3, Bedrock). | Vendor lock-in. | **Adopt** | If multi-cloud requirements emerge from enterprise customers. |
| **Ollama** | Local AI | Runs Llama 3 / Mistral locally for free. | Requires heavy GPU resources on the host. | **Adopt** | Only usable if the host environment has sufficient VRAM. |
