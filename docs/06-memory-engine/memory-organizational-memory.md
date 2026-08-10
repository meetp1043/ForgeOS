# Organizational Memory

Organizational Memory sits at the top of the memory hierarchy. It represents the shared, universally applicable knowledge base of the entire company or tenant.

## Definition
Cross-project, foundational knowledge that dictates how the organization operates, builds software, and ensures compliance.

## Examples
- **Engineering Standards**: "All APIs must be documented using OpenAPI 3.0."
- **Security Policies**: "No plain-text passwords may be logged under any circumstances."
- **Approved Technology Patterns**: "The standard cloud provider for all new projects is AWS."
- **Architecture Principles**: "Prefer modular monoliths over microservices for initial MVP development."

## Characteristics
- **Owner**: Organization (Tenant Administrators, Principal Architects)
- **Scope**: Organization (Tenant)
- **Retention**: Indefinite
- **Access**: Read-access available to all agents across all projects within the tenant.
- **Typical Retrieval**: Project initialization, architecture review, security audits, and agent onboarding.

## Governance
Because Organizational Memory affects every project, it is highly restricted. Standard agents cannot write or update Organizational Memory. It requires a formal governance workflow and explicit human approval.
