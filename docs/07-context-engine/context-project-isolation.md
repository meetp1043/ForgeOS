# Project Isolation

Within a single Tenant, data is subdivided into Projects. Projects often have different codebases, different team members, and different compliance requirements.

## The Project Boundary
Project A context cannot automatically retrieve Project B memory or code. 

If an agent is assigned to fix a bug in Project A, the Context Engine will strictly filter out ADRs, source code, and memory from Project B, even if they appear semantically similar or highly relevant.

## Cross-Project Access
Explicit cross-project access requires formal authorization. 
- Example: An "Enterprise Architect" agent is tasked with standardizing authentication across Project A, Project B, and Project C. 
- In this specific case, the `ContextRequest` must explicitly list `[ProjectA, ProjectB, ProjectC]` in its scope array, and the Security System must authorize this cross-project read before the Context Engine fetches the data.
