# Cost Routing & Budget Control

(Future Phase)

## Policy
`ModelCostClass` tracks `FREE_LOCAL`, `LOW_COST`, `PREMIUM`.
The Router can reject requests that ask for `PREMIUM` if the tenant's budget is depleted.

## Token Tracking
`ModelResponse` tracks `inputTokens` and `outputTokens`. These will be hooked into the billing/audit modules.
