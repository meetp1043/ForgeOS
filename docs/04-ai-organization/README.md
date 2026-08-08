# ForgeOS AI Organization

## Purpose
This directory contains the definitive specification for the ForgeOS AI Software Engineering Organization. It defines the specialized roles, hierarchy, authority, and operating rules for the AI agents that power the system.

## Core Concept
ForgeOS is not a single, monolithic coding chatbot. It is an organization composed of distinct, specialized AI agents. Each agent acts as a professional with defined boundaries, responsibilities, tools, and escalation paths.

## Documentation Structure
- **Organizational Structure**: The hierarchy and taxonomy of agent layers (`agent-hierarchy.md`, `executive-layer.md`, etc.).
- **Agent Roles**: Specific definitions of the roles available in the ForgeOS ecosystem.
- **Operating Models**: How agents interact, delegate, escalate, and utilize memory/tools (`agent-communication-model.md`, `agent-tool-model.md`, etc.).
- **Governance**: Security, creation, evaluation, and retirement rules for agents.

## Implementation Constraint
This specification dictates *what* the agents are and *how they should behave conceptually*. The actual implementation of these rules (prompts, tool bindings, Java runtime execution) belongs in the core codebase and is dictated by `/docs/03-architecture/`.
