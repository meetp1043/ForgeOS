# Workflow Overview

## Purpose
The Workflow Engine is the central coordinator of ForgeOS. It takes a high-level user objective and orchestrates every phase of the software engineering lifecycle through a durable, auditable state machine.

## What the Workflow Engine Does
1. Receives an objective (e.g., "Build me an e-commerce platform").
2. Triggers the planning phase (Business Analysis → Requirements → Architecture).
3. Decomposes plans into discrete, dependency-aware tasks.
4. Assigns tasks to the appropriate AI agents based on role, capability, and availability.
5. Tracks execution, handling success, failure, retry, and escalation.
6. Enforces approval gates at risk boundaries (e.g., production deployment).
7. Persists all state durably so that a server restart never loses progress.
8. Emits events for observability and auditing.

## What the Workflow Engine Does NOT Do
- It does **not** contain business logic. It does not know how to write Java code or design a database schema — that knowledge belongs to the agents.
- It does **not** interact directly with LLM APIs. Agent execution is delegated to the Agent Runtime.
- It does **not** make product decisions. Those are made by the Product Manager agent and approved by humans.

## The Coordination Flow
```
User Objective
  → Planning (Business Analysis, Requirements, Architecture)
    → Task Decomposition (Project Manager, Engineering Manager)
      → Agent Assignment (Orchestrator selects agents)
        → Task Execution (Agents use tools in the Sandbox)
          → Verification (QA, Code Review, Security)
            → Approval (Human gates for high-risk actions)
              → Completion (Artifacts committed, deployed)
```
