# Engineering Principles

These principles guide all technical decisions within ForgeOS.

1. **Production Quality over Demo Quality**: Do not write hacky or brittle code. 
2. **Security by Design**: Security is not an afterthought. Secure defaults, least privilege, and zero trust where applicable.
3. **Modular Architecture**: High cohesion, low coupling. Start with a modular monolith.
4. **Clear Separation of Responsibilities**: UI, business logic, and infrastructure must be strictly isolated.
5. **Explicit Agent Responsibilities**: AI agents must only operate within their defined domains and scopes.
6. **Least-Privilege Permissions**: Both code and AI agents must have the minimum permissions necessary to function.
7. **Human Approval for High-Risk Operations**: Automated deployments, infrastructure destruction, and massive refactors require human oversight.
8. **Full Auditability**: Log who (human or AI), what, when, and why for significant system actions.
9. **Version-Controlled Artifacts**: Code, infrastructure as code, prompts, and documentation must live in Git.
10. **Testability**: Code must be written in a way that is easily unit and integration testable.
11. **Observability**: Expose metrics, logs, and traces.
12. **Maintainability**: Prefer readable, simple code over clever, complex code.
13. **Cost Awareness**: Optimize AI API usage, cloud resources, and processing time.
14. **Reproducibility**: Builds, tests, and agent workflows must be deterministic and reproducible.
15. **Backward Compatibility**: Ensure APIs and databases maintain backward compatibility where practical.
16. **No Hard-Coded Secrets**: Period.
17. **No Credentials in Git**: Use environment variables and secrets managers.
18. **No Silent Architecture Changes**: All architecture shifts must be documented in an ADR.
19. **No Unnecessary Dependencies**: Keep the supply chain small and secure.
20. **No Premature Microservices**: Prove the domain boundaries in a monolith first.
21. **Design Module Boundaries**: Prepare for future service extraction.
22. **Asynchronous Processing**: Use message queues for non-blocking operations.
23. **Recoverable Workflows**: State must be persisted at boundaries so workflows can resume.
24. **Traceable AI Decisions**: Why did an agent make a change? It must be logged.
