# Workflow Risks

| Risk | Impact | Likelihood | Mitigation |
| :--- | :--- | :--- | :--- |
| **Workflow Deadlock** | Two steps waiting for each other indefinitely. | Medium | Circular dependency detection at `VALIDATING` time. Timeout enforcement at runtime. |
| **Infinite Loops** | A step repeatedly fails and retries forever. | High | Hard `max_attempts` limit on all retry policies. Global retry cap per workflow. |
| **Runaway Agents** | An agent consumes excessive tokens without producing results. | High | Per-step token limits. Per-workflow budget enforcement. Agent timeout enforcement. |
| **Duplicate Execution** | A step is dispatched twice due to a race condition. | Medium | Idempotency keys. Deduplication table. Optimistic locking on step status. |
| **State Corruption** | A crash during a state transition leaves inconsistent data. | Low | Write-ahead persistence. Database transactions. Optimistic locking. |
| **Approval Bottleneck** | Humans are slow to approve, blocking workflows for days. | High | Approval timeout with configurable expiration behavior. Dashboard notifications. |
| **Excessive Retries** | Retries on non-transient errors waste tokens. | Medium | Strict classification of retryable vs. non-retryable errors. |
| **Cost Explosion** | Many parallel agents consuming frontier models simultaneously. | High | Real-time budget tracking. Warning thresholds. Hard budget caps. |
| **Inconsistent State** | Partial compensation leaves the system in an undefined state. | Medium | Compensation failures escalate immediately to human. Manual recovery documented. |
| **Failed Compensation** | The "undo" action itself fails. | Medium | Compensation failures are never retried automatically. Escalate to human immediately. |
| **Malicious Workflow Input** | A crafted input exploits the workflow engine. | Low | Input validation at `VALIDATING` time. Sandboxed execution. No direct SQL or shell from inputs. |
