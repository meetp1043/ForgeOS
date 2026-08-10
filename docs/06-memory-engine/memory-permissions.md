# Memory Permissions

Agents in ForgeOS do not receive unrestricted access to the entire memory database. Retrieval is tightly coupled to the system's permission model.

## Access Determinants

Access to a memory entry depends on a matrix of factors:

1. **Role**: A Code Review Agent has read access to architecture memory, but a generic Web Search Agent does not.
2. **Project**: Agents are provisioned into a project. They cannot read memory outside their provisioned project ID.
3. **Task**: Specific memories might be locked to a task boundary.
4. **Tenant**: Absolute isolation boundary.
5. **Sensitivity**: A memory tagged `RESTRICTED` (e.g., a proprietary algorithm design) might require explicit human user permission for an agent to read.
6. **Agent Permission**: The intrinsic capabilities granted to the agent upon creation.
7. **User Permission**: The permissions of the human who invoked the agent (Agents inherit the constraints of their invoking user).
8. **Security Classification**: Ties into the broader ForgeOS data classification schema.

## Enforcement
Permissions are enforced as mandatory filters at the database retrieval layer. The embedding search or SQL query must physically include the permission boundary parameters, ensuring the LLM router or vector database cannot accidentally bypass the check.
