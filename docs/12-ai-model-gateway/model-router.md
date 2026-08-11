# Model Router

The `ModelRouter` evaluates the `ModelRequest` and selects an appropriate provider.

## Routing Priority
1. **Security/Privacy constraints**: A request classified as `RESTRICTED` will never be sent to a provider whose max allowed privacy is `CONFIDENTIAL`.
2. **Tenant Policy**: (Future Phase) Tenant-specific whitelists.
3. **Agent Policy**: Evaluates `LOCAL_FIRST`, `QUALITY_FIRST`, etc.

If no provider satisfies the requirements, it throws a `ModelGatewayException`.
