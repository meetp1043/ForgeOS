# Container Architecture

This document defines the high-level deployable units (containers) of the ForgeOS system.

## Container Diagram

```mermaid
C4Container
    title Container Architecture for ForgeOS

    Person(user, "User")

    Container(webapp, "Web Application", "Next.js, React", "Provides the SPA UI and conversational interface.")
    
    Container(core_api, "ForgeOS Core API", "Java, Spring Boot", "The modular monolith housing all orchestration, workflow, and agent logic.")
    
    ContainerDb(db_relational, "Relational Database", "PostgreSQL", "Stores projects, tasks, user data, and state.")
    ContainerDb(db_document, "Document/Vector Store", "MongoDB / Vector DB", "Stores agent memory, large artifacts, and context embeddings.")
    ContainerDb(cache, "Cache & Pub/Sub", "Redis", "Handles session state, fast locks, and internal caching.")
    Container(broker, "Message Broker", "RabbitMQ", "Handles durable asynchronous tasks and event routing.")
    
    Container(sandbox, "Execution Sandbox", "Docker Engine / K8s", "Isolated environment where agents run bash commands, compilers, and tests.")

    Rel(user, webapp, "Uses", "HTTPS")
    Rel(webapp, core_api, "Makes API calls to", "JSON/REST/WebSocket")
    
    Rel(core_api, db_relational, "Reads/Writes", "JDBC")
    Rel(core_api, db_document, "Reads/Writes", "Driver")
    Rel(core_api, cache, "Reads/Writes", "Redis Protocol")
    Rel(core_api, broker, "Publishes/Subscribes", "AMQP")
    
    Rel(core_api, sandbox, "Spawns and controls isolated tasks", "Docker API / gRPC")
```

## Primary Containers

1. **Web Application**: Static output served by Next.js or a CDN, running React on the client side for highly interactive visualizations.
2. **ForgeOS Core API**: The Spring Boot modular monolith. It scales horizontally behind a load balancer.
3. **Execution Sandbox**: A crucial separation. We do not run `npm install` or `mvn test` inside the Core API container. The Core API manages ephemeral Sandbox containers to execute untrusted agent commands securely.
4. **Data Tier**: 
    - **PostgreSQL**: Strict relational data (Users, Projects, Tasks, Billing).
    - **MongoDB/VectorDB**: Unstructured data, large text blobs, and RAG embeddings for the Context Engine.
