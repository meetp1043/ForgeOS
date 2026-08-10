# Agent Evaluation

Agent Evaluation is the continuous process of measuring an agent's quality and effectiveness.

## Core Evaluation Metrics

The Framework tracks the following to evaluate an Agent Version:
- **Task Success Rate**: Percentage of tasks that reach `COMPLETED` without human override.
- **Correctness**: LLM-as-a-judge score evaluating if the agent followed instructions.
- **Review Acceptance Rate**: How often the agent's PRs are approved by the Code Review Agent on the first try.
- **Defect Rate**: Bugs introduced by this agent version discovered in later testing phases.
- **Test Success Rate**: Percentage of time the agent writes passing tests.
- **Security Findings**: Number of vulnerabilities introduced by the agent (caught by SAST tools).
- **Cost Efficiency**: Dollar spend per successful task.
- **Latency**: Time to completion.
- **Tool Success Rate**: How often the agent correctly formats tool JSON vs throwing syntax errors.
- **Escalation Rate**: How often the agent gives up and calls for help.
- **False Completion Rate**: How often the agent claimed success, but the Validation Policy rejected its evidence.

Agents that fall below baseline thresholds are flagged for `SUSPENDED` status.
