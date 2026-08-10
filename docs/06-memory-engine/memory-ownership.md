# Memory Ownership

To manage the lifecycle and authority of memory, ForgeOS assigns explicit ownership to every memory entry. 

Ownership defines who or what has the ultimate right to update, delete, or classify the memory.

## Ownership Mappings

- **User Preferences** $\rightarrow$ Owned by the **User**. Only the user can delete or update these.
- **Project Decisions** $\rightarrow$ Owned by the **Project** (represented by the Product Manager or Principal Architect role).
- **Architecture Decisions** $\rightarrow$ Owned by the **Project** or **Architecture Domain**.
- **Agent Experience** $\rightarrow$ Owned by the **Agent** (or the overarching Agent Fleet manager).
- **Organizational Engineering Principles** $\rightarrow$ Owned by the **Organization** (Tenant admin).

## Ownership Conflicts

When ownership dictates authority, conflicts must be handled logically:
- If an individual Agent tries to overwrite an Organizational Engineering Principle, the operation is blocked due to an ownership hierarchy mismatch.
- If a User attempts to delete a Project Decision, it is blocked unless the User holds administrative ownership over the Project.

Ownership aligns directly with the ForgeOS Role-Based Access Control (RBAC) defined in the Governance and AI Organization specifications.
