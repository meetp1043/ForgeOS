# Deployment Architecture

ForgeOS is designed for cloud-native deployment.

## Environments
- **Development**: Local environment running via `docker-compose` (Spring Boot + Postgres + Redis).
- **Staging**: A replica of production used for pre-release testing.
- **Production**: The live environment serving users.

## Containerization
- The `Core API` and `Web Application` are packaged as immutable Docker images.
- CI/CD (GitHub Actions) builds these images on every push to the `main` branch.

## Reference Architecture (AWS)
- **Compute**: Amazon ECS (Fargate) for the stateless Core API and Web App.
- **Database**: Amazon RDS (PostgreSQL).
- **Cache/Broker**: Amazon ElastiCache (Redis) and Amazon MQ (RabbitMQ).
- **Execution Sandbox**: A separate, heavily firewalled ECS cluster or dedicated EC2 fleet that runs the untrusted agent Docker commands.
- **Future Target**: Kubernetes (EKS) will be evaluated if custom autoscaling rules for Sandbox containers become too complex for ECS.
