# Agent Memory Security

Memory access requires explicit authorization.

## Read Security
Agents cannot read unrestricted global memory. When the Agent Framework requests context, it passes the agent's Role and Tenant ID. The Context/Memory engines use this to filter out restricted facts (e.g., a standard engineer cannot query the Memory Engine for HR or payroll data unless their role permits it).

## Write Security
Agents cannot modify authoritative memory without permission. 
- The Framework blocks a standard `Backend Engineer` from publishing an `Architecture Decision` fact to the Memory Engine.
- Agents are strictly isolated by Tenant. Under no circumstances can an agent from Tenant A write a fact into Tenant B's memory graph.

If an agent attempts an unauthorized memory modification, the Framework throws a `MEMORY_ERROR` and logs a security violation.
