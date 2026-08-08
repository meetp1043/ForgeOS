# ForgeOS AI Agent Engineering Constitution

Welcome to the ForgeOS repository. This document serves as the primary instruction manual and constitution for all AI agents operating within this project.

## Project Overview
ForgeOS is an AI Software Engineering Operating System. It functions as a complete AI software company composed of specialized hierarchical AI agents that collaborate to understand ideas, design architecture, write code, run tests, review security, deploy, and maintain software. The system is designed to generate production-quality applications in various technologies based on user requirements.

## Architecture Principles
1. **Production Quality**: Code must be robust, scalable, and maintainable. No demo-quality shortcuts.
2. **Modular Architecture**: Prefer a modular monolith initially. Design strict module boundaries to allow future extraction into microservices if needed.
3. **Security by Design**: Implement security at every layer. Least privilege, secure defaults.
4. **Separation of Concerns**: Clear boundaries between domains, logic, and infrastructure.

## Repository Rules
- Do not modify files outside your explicit assignment unless necessary for cross-cutting concerns (and only with approval).
- Preserve existing documentation and prompts unless they are explicitly deprecated.
- Adhere strictly to the defined governance in `/docs/00-governance/`.

## Documentation Rules
- `/docs/README.md` outlines the documentation hierarchy.
- All major architecture decisions must be documented using the Architecture Decision Record (ADR) template in `/templates/architecture-decision-record.md`.
- Keep documentation up to date alongside code changes.

## Coding Rules
- Follow language-specific idiomatic practices.
- Ensure all AI-generated code is understandable by human engineers.
- No hard-coded secrets, tokens, or credentials.
- No silent architecture changes; escalate ambiguous requirements.
- No unnecessary dependencies.

## Security Rules
- Apply least-privilege permissions.
- Validate all inputs.
- Assume all generated code will be security audited.
- Never log sensitive data (PII, passwords, keys).

## Testing Rules
- Every feature must have unit and integration tests where applicable.
- The system must be highly testable. Ensure mocking and dependency injection are supported.

## Git Rules
- Make atomic, focused commits.
- Follow Conventional Commits format for all commit messages.
- Do not commit secrets. Run `/scripts/check-secrets.ps1` before committing.
- Do not blindly `git add -A`. Review all staged files.

## Agent Rules
- Explicitly state your assumptions.
- Stop and report ambiguity instead of guessing.
- Tracing: Every critical workflow must be recoverable, and every important AI decision must be traceable.
- Human Approval: Always require human approval for high-risk operations (e.g., destructive actions, production deployments).

## Dependency Rules
- Only introduce new dependencies if absolutely necessary and approved.
- Use well-maintained, popular, and secure libraries.

## Database Rules
- Schema changes must be versioned (e.g., Flyway/Liquibase).
- Ensure backward compatibility in database migrations.

## API Rules
- Use standard REST or GraphQL patterns.
- Version APIs.
- Provide comprehensive error messages and standard HTTP status codes.

## Error Handling Rules
- Handle errors gracefully and recoverably.
- Provide meaningful context in exceptions without leaking system internals.

## Logging & Observability Rules
- Use structured logging (e.g., JSON format).
- Include trace IDs for cross-service or multi-agent correlation.

## Secrets Management Rules
- All secrets must be injected via environment variables or a secret manager.
- Absolutely no credentials committed to Git.

## Change Management Rules
- Follow the process defined in `/docs/00-governance/change-management.md`.

## Definition of Done
- Code implemented according to requirements.
- Tests passing.
- Code reviewed (AI or human).
- Documentation updated.
- ADR created if architecture changed.
- Security baseline met.
