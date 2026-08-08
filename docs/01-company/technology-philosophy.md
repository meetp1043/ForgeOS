# Technology Philosophy

This document defines the technology principles for ForgeOS itself. 

**IMPORTANT DISTINCTION:** ForgeOS itself must have a stable, robust technology foundation. However, ForgeOS must be strictly **technology-agnostic regarding the applications it builds**.

ForgeOS may eventually generate applications using React, Angular, Vue, Spring Boot, Node.js, Python, FastAPI, .NET, Flutter, mobile technologies, AI/ML stacks, and more. 

Never confuse the ForgeOS implementation technology with the technology selected for generated applications.

## Primary ForgeOS Stack

The ForgeOS core system is built upon the following technologies to ensure enterprise-grade stability, scalability, and maintainability:

### Backend
- **Java 21**: Leveraging modern Java features (virtual threads, records).
- **Spring Boot 3.x**: The foundation for our application framework.
- **Spring AI**: Standardized abstractions for interacting with various AI models.
- **Spring Modulith**: Enforcing modular monolith boundaries to prepare for future microservice extraction.
- **Spring Security**: Robust authentication and authorization.
- **PostgreSQL**: Primary relational datastore for relational states.
- **MongoDB**: Utilized where schema flexibility is strictly required (e.g., unstructured AI context).
- **Redis**: Caching and high-speed temporary state.
- **RabbitMQ**: Initial message broker for asynchronous agent orchestration.
- **Kafka**: Future scaling option for massive event-driven throughput.

### Frontend
- **Next.js**: React framework for server-side rendering and static generation.
- **React**: Component-based UI library.
- **TypeScript**: Strict typing for frontend reliability.
- **Tailwind CSS**: Utility-first CSS framework for rapid styling.

### Infrastructure
- **Docker**: Containerization for consistent environments.
- **GitHub Actions**: CI/CD automation.
- **AWS**: Primary cloud reference architecture.
- **Kubernetes**: Later scaling target for orchestrating agent containers.

### AI and Models
- **OpenAI / Anthropic / Google Gemini**: Commercial frontier models for complex reasoning.
- **Ollama / Open-source models**: Support for local, privacy-preserving execution.
- **BYOK (Bring Your Own Key)**: Allowing organizations to utilize their own provider agreements.
- **LOCAL-FIRST AI**: Prioritizing the ability to run the system entirely locally where hardware permits.
