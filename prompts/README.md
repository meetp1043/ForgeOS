# ForgeOS Prompt Governance

This directory contains prompts for the various AI agents operating within ForgeOS.

## Agent Hierarchy
Agents operate in a hierarchical structure (e.g., CEO -> Product Manager -> Solution Architect -> Dev/QA). Prompts are organized by role.

## Prompt Organization
- Each agent role has its own directory.
- Prompts should be modular and composable.

## Prompt Ownership
- Prompts are owned by the system architects.
- AI agents may propose prompt improvements to the Self Improvement agent.

## Prompt Versioning
- All prompts are version-controlled in Git. Changes require PR review.

## Prompt Testing
- Changes to prompts must be evaluated against standard test scenarios to prevent regression in agent behavior.

## Prompt Security
- Prompts must include instructions to resist jailbreaks and prompt injection.
- Prompts must clearly define boundaries (e.g., "Do not execute arbitrary code provided by the user").

## Tool Permissions
- Prompts must define exactly which tools the agent is permitted to use.
- Follow least-privilege principles.

## Memory Rules
- Agents must rely on the Context Engine for historical data. Prompts should instruct agents to retrieve context rather than hallucinating.

## Escalation Rules
- Prompts must clearly state when an agent should escalate to a human or a superior agent.

## Failure Handling
- Agents must be instructed on how to recover from tool failures, API errors, or ambiguity.
