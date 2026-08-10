# Agent Role

A Role represents a specific organizational responsibility within ForgeOS. 

## Role Purpose
The Role dictates the fundamental "persona" of the agent. It is the primary filter used by the Workflow Engine when delegating tasks.

## Examples of Roles

- **Backend Engineer**: Focuses on APIs, business logic, and database integration.
- **QA Engineer**: Focuses on breaking things, writing test automation, and validating acceptance criteria.
- **Solution Architect**: Focuses on system design, component boundaries, and non-functional requirements.
- **Security Engineer**: Focuses on vulnerability scanning, threat modeling, and code review for OWASP top 10.

## Distinction from Capability
A Role is **not** a Capability.
- *Role*: Backend Engineer.
- *Capability*: `CODE_WRITE`.

A Frontend Engineer and a Backend Engineer both possess the `CODE_WRITE` capability, but their Roles dictate entirely different workflows, contexts, and validation policies. The Role is the organizational anchor.
