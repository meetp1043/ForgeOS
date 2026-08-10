# Spring Boot Foundation (Phase 09)

## Overview

This directory documents the foundational backend architecture for ForgeOS. Phase 09 established a single Spring Boot application acting as a Modular Monolith.

## Purpose

The backend serves as the core orchestration and execution engine for all AI agents, workflows, and context retrieval in ForgeOS. 

## Architectural Principles

1. **Modular Monolith**: We start with a single deployment unit (`forgeos-backend`).
2. **Strict Boundaries**: We use Spring Modulith to enforce boundaries between distinct domain areas (e.g., `agent`, `workflow`, `context`).
3. **Provider Agnosticism**: Business logic must never depend directly on a specific LLM provider's API (e.g., OpenAI).
4. **Separation of Concerns**: Domain logic is decoupled from infrastructure logic (web controllers, database repositories).

## Documentation Index

- [Backend Architecture](backend-architecture.md)
- [Module Boundaries](module-boundaries.md)
- [Dependency Rules](dependency-rules.md)
- [Configuration](configuration.md)
- [Database Strategy](database-strategy.md)
- [Redis Strategy](redis-strategy.md)
- [Messaging Strategy](messaging-strategy.md)
- [Error Handling](error-handling.md)
- [Logging Strategy](logging.md)
- [Testing Strategy](testing-strategy.md)
- [Security Foundation](security-foundation.md)
