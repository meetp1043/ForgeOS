# Context Validation

Before the `Context Package` is marked `READY` and delivered to the Agent Runtime, it must pass a final validation gate. This is the last line of defense against logic bugs in the Context Engine itself.

## Validation Checklist

The validation layer confirms:
- **Tenant**: Does every item in the package belong to the requested TenantID?
- **Project**: Do the items match the permitted ProjectID scope?
- **Permissions**: Are there any items flagged with a classification (e.g., `RESTRICTED`) that the agent's role does not possess?
- **Source Authority**: Was the highest-authority architectural decision included, or was it accidentally truncated?
- **Freshness**: Are there any items flagged as stale that lack historical annotations?
- **Required Information**: Is the immediate task objective present? (A package without a task is useless).
- **Token Budget**: Does the final package size fit within the requested limit?
- **Security**: Have all external/untrusted blocks been wrapped in data delimiters?
- **Conflicts**: Were all contradictions resolved or explicitly annotated?

## Fail Closed
If a high-risk workflow (e.g., `Deploy to Production`) triggers a validation failure (e.g., a critical security policy artifact is missing), the Context Engine must **Fail Closed**. It throws an exception to the Workflow Engine, halting the agent, rather than letting the agent guess the missing policy.
