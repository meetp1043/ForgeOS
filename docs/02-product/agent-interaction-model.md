# Agent Interaction Model

ForgeOS abstracts the complexity of coordinating multiple AI specialists. The user interacts with the system, and the system manages the agents.

## Core Principles

### Unified Interface
Users should not need to manually message the "Backend Agent," then copy the response to the "QA Agent." The user communicates their intent to the ForgeOS Orchestrator or the Executive Agent (e.g., PM), which handles internal delegation.

### Organizational Transparency
While the user doesn't *have* to manage specialists, the system must remain completely transparent. The UI will show exactly which agent is working on which task, what their current thought process is, and what files they are modifying.

### Escalation
If a specialist agent (e.g., Database Engineer) encounters an ambiguous requirement, it escalates to the Project Manager. If the Project Manager cannot resolve it using project memory, it escalates to the Human User with a clear clarifying question.

### Approvals
Agents prepare work and pause at predefined gates. They notify the user via the unified interface, presenting a summary of the action for approval (e.g., "DevOps Engineer is requesting approval to deploy to Production").

### Summaries
Rather than flooding the user with raw agent logs, the Executive agents synthesize the work of specialists into concise, high-level summaries (e.g., "The Backend team completed the Authentication module. 34 tests passed. Awaiting your review.").

## Direct Interaction (Override)
While the unified interface is the default, advanced users can "zoom in" and interact directly with a specialist agent via the task view to provide micro-level coaching or specific technical overrides (e.g., telling the Frontend Agent exactly which CSS class to use).
