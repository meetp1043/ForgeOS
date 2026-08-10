# Agent Isolation

Agent Isolation defines agent-specific context permissions based on their assigned role and current objective.

## Differential Context
Different agents looking at the same project at the same time may see completely different information.

### Examples
- **Security Agent**: Tasked with auditing a PR. It receives access to known vulnerabilities, CVE databases, security-relevant artifacts, and the full PR diff. It does *not* receive UX design guidelines.
- **Cost Optimization Agent**: Tasked with reducing AWS spend. It receives access to cost dashboards, infrastructure-as-code files, and utilization metrics. It does *not* receive unlimited access to user PII stored in the database.

## Principle of Least Privilege
Neither agent automatically gets unrestricted access to the entire repository. The Context Engine ensures that the payload is strictly aligned with the agent's persona. This prevents role-confusion (e.g., a Database Engineer agent suddenly trying to write CSS because it saw a React component in its prompt).
