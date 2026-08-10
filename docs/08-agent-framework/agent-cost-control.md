# Cost Control

Autonomous agents running in a loop can generate massive cloud bills within minutes if unconstrained. The Agent Framework implements strict cost circuit breakers.

## Tracked Metrics
The Framework monitors the following for every execution:
- **Input Tokens**: Prompt size.
- **Output Tokens**: Generation size.
- **Model Cost**: Calculated dynamically based on the current model's pricing tier.
- **Tool Cost**: Usage of paid external APIs (e.g., SerpApi for web search).
- **Execution Duration**: Compute time utilized.

## Budget Hierarchy
- **Per-Execution Budget**: A hard cap on a single atomic run (e.g., $1.00). If exceeded, the agent fails with `BUDGET_EXCEEDED`.
- **Per-Task Budget**: Cap for the entire Jira ticket, including retries and delegations.
- **Per-Project Budget**: Cap for the month.
- **Per-Tenant Budget**: Total organizational billing limit.

## Escalation
If a task requires 100k tokens of context and 50 LLM loops, it will quickly blow past a standard $2.00 task budget. The Framework will pause the agent and escalate to a Manager Agent or Human to request a budget increase before continuing. Security and Reliability must not be sacrificed merely to stay under a cost threshold (i.e., do not skip security checks because they cost tokens).
