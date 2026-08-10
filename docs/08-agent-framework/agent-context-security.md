# Agent Context Security

The Context Engine handles the filtering of data, but the Agent Framework enforces the final boundary.

## Trust but Verify
The Agent Framework trusts that the `Context Package` provided by the Context Engine has been filtered for Tenant and Project isolation. 

However, the Agent Framework enforces the following rule:
**Context does not grant permissions.**

If a bug in the Context Engine accidentally includes the text of a `CRITICAL` deployment script in the context of a Junior Frontend Agent, the agent *still* cannot execute that script. The Framework's Tool Execution layer will block the agent because its `Agent Definition` lacks the `DEPLOY_PRODUCTION` permission.

Context is informational; it is never authoritative for execution rights.
