# Agent Delegation Model

Delegation ensures that complex tasks are broken down and handled by specialists.

## Delegation Chains
Delegation strictly follows the organizational hierarchy:
- `CEO` → `Product Manager`
- `Product Manager` → `Business Analyst`
- `Product Manager` → `Solution Architect`
- `Project Manager` → `Engineering Manager`
- `Engineering Manager` → `Backend Engineer`
- `Engineering Manager` → `Frontend Engineer`
- `Engineering Manager` → `Database Engineer`
- `QA` → `Code Review` (for test code review)

## Delegation Constraints
- **Authority Boundary**: An agent cannot delegate a task outside its own authority. (e.g., A Frontend Engineer cannot delegate a database migration to a DB Engineer; it must ask the Engineering Manager to assign it).
- **Accountability**: The delegating agent is ultimately responsible for verifying the result of its delegates before bubbling the success up the chain.
