# Collaboration Model

ForgeOS supports human-to-human collaboration alongside human-to-AI collaboration. 

## Project Roles and Permissions

- **Owner**: Full control over the project, billing, agent configurations, and destructive actions (e.g., archiving the project).
- **Administrator**: Can manage workspace settings, invite users, and approve all High/Critical risk actions.
- **Developer**: Can view code, interact with agents, manually commit code, and approve Medium risk actions (e.g., standard PR merges).
- **Reviewer**: Can view code, comment on PRs, and reject AI actions, but cannot directly push code or trigger deployments.
- **Product Manager**: Can interact with the Business Analyst and Product agents to define requirements, but cannot approve code merges or deployments.
- **Viewer**: Read-only access to the dashboard, architecture diagrams, and conversational queries (cannot issue commands that change state).

*Note: These are conceptual permission boundaries that will guide the implementation of the RBAC system.*
