# Agent History

An agent is rarely the first entity to attempt a complex task. Previous agent executions—especially failures—provide critical context for course correction.

## Historical Context Elements
If an agent is picking up a task that was previously attempted, the context should include:
- **Previous Task**: What was the exact assignment?
- **Result**: Did it succeed, fail, or time out?
- **Failures**: What exactly broke? (e.g., Stack trace of the test failure).
- **Attempted Approach**: A brief summary of what the previous agent tried to do.
- **Review Feedback**: Comments from human or AI Code Reviewers on the previous attempt.
- **Important Discoveries**: "The legacy auth API actually expects XML, not JSON."

## The "Untrusted" Constraint
**Do not blindly trust previous agent output.**
Agent history has a low `Authority` score. If a previous agent failed, its assumptions might be fundamentally flawed. The Context Engine presents this history as a *warning* or *hint*, not as an authoritative directive.
