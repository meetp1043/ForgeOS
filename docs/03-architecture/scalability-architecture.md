# Scalability Architecture

ForgeOS will start as a Modular Monolith but must be designed to scale.

## Scaling the Monolith
- The Spring Boot Core API is stateless. It can be horizontally scaled behind a load balancer simply by adding more instances.
- PostgreSQL handles transactional load. We will use connection pooling (HikariCP) and scale vertically before considering read-replicas.

## Future Microservice Extraction
Because we strictly adhere to Spring Modulith boundaries, if a specific domain becomes a bottleneck, it can be cleanly extracted.
Potential future microservices (only when justified by operational data):
- **Execution Sandbox API**: If provisioning secure containers requires deep Kubernetes integration that bloats the core API.
- **Model Gateway**: If we need a dedicated edge service just to handle heavy load balancing and caching across dozens of LLM providers.
- **Memory Engine**: If the vector database queries become too CPU-intensive for the core API instances.
