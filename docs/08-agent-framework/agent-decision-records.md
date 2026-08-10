# Agent Decision Records

This document tracks major architectural decisions regarding the Agent Framework.

## ADR-001: Separation of Definition and Instance
- **Date**: 2024-05-10
- **Status**: Accepted
- **Context**: We need to track historical agent behavior accurately.
- **Decision**: We will separate `Agent Definition` (static, versioned) from `Agent Instance` (runtime, volatile).
- **Consequences**: This requires a more complex Agent Registry, but guarantees historical auditability.

## ADR-002: Deterministic Evidence Verification
- **Date**: 2024-05-12
- **Status**: Accepted
- **Context**: LLMs hallucinate test results.
- **Decision**: The Framework will strip the `evidence` block from the LLM's output and replace it with the actual captured stdout/stderr from the Tool Sandbox.
- **Consequences**: Agents cannot fake success. Sandboxing must be tightly integrated with the Result Validator.

## ADR-003: No Universal Agent
- **Date**: 2024-05-15
- **Status**: Accepted
- **Context**: A single "Super Agent" with all tools is too dangerous and wastes tokens.
- **Decision**: Agents will be strictly role-based (Backend, QA, DevOps) with least-privilege permissions.
- **Consequences**: Requires robust Delegation and Escalation workflows.
