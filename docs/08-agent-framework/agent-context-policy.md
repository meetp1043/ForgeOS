# Agent Context Policy

The Context Policy defines exactly what types of information an agent instance requests from the Context Engine during execution.

## Principle of Minimal Context
Agents must specify required context constraints to prevent context window explosion and minimize token costs. An agent does **not** automatically receive the entire repository.

## Example: Backend Engineer Context Request
A typical Backend Engineer agent policy requires:
- The immediate task description.
- Active requirements / acceptance criteria.
- Relevant Architectural Decision Records (ADRs).
- API contracts (OpenAPI specs) related to the task.
- Source code of the target module and immediate dependencies.
- Relevant unit tests.
- Established coding standards.

It explicitly **excludes**:
- Unrelated project repositories.
- Unrelated personal/HR data.
- Frontend CSS/HTML files (unless working on a full-stack boundary).
- Raw credentials.

The Agent Framework passes this policy to the **Context Engine**, which performs the actual retrieval, filtering, and packaging.
