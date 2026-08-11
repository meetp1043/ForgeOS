# ForgeOS Tool Execution System (Phase 13)

The Tool System is the critical security boundary that protects the ForgeOS host environment from malicious or hallucinated AI instructions. AI agents *never* have direct, unrestricted access to the host machine.

## Architecture

```mermaid
flowchart TD
    AR[Agent Runtime] -->|Tool Request| GW(Tool Executor)
    
    GW -->|Lookup| TR(Tool Registry)
    
    GW -->|1. Validate Context| Auth(Tool Authorizer)
    Auth -->|2. Check Permission| Auth
    Auth -->|3. Evaluate Risk| Auth
    Auth -->|4. Request Approval| Appr{Approval System}
    
    GW -->|5. Verify Arguments| Val(Argument Validation)
    
    GW -->|6. Isolate Environment| SB(Sandbox)
    SB --> Tool[Tool Adapter]
    Tool -->|Syscalls| Ext[External System]
    
    GW -->|7. Sanitize Output| OV(Output Validation)
    GW -->|8. Audit Trail| Aud(Audit Logs)
    
    GW -->|Tool Result| AR
```

## Core Principles
1. **Never trust AI arguments**: Path traversals (`../`) and command injections must be stripped or blocked structurally.
2. **Deny by default**: Tools require explicit RBAC permissions based on the tenant context.
3. **Risk-based execution**: `CRITICAL` tools (like production deployment) are blocked outright until Phase 19 (Sandbox Execution) is fully implemented.
