# Token Budget

The Token Budget dictates exactly how much information the Context Engine is allowed to pack into the prompt. Token budgeting prevents API errors (`context_length_exceeded`) and controls financial costs.

## Budget Allocations

A standard Context Package budget is subdivided to ensure that no single source starves the others:

- **System Budget**: Reserved for ForgeOS hardcoded instructions. (Non-negotiable).
- **Role Budget**: Reserved for the agent persona definitions.
- **Task Budget**: Reserved for the immediate objective and acceptance criteria.
- **Memory Budget**: Cap on historical/semantic knowledge.
- **Code Budget**: The largest variable allocation, reserved for source files.
- **Tool-Result Budget**: Cap on raw terminal output or test logs.
- **Safety Reserve**: A small buffer (e.g., 500 tokens) to account for tokenization discrepancies between the Context Engine's estimator and the provider's actual tokenizer.
- **Output Reservation**: The tokens explicitly reserved for the model's response. (Context Budget = Total Context Window - Output Reservation).

## Dynamic Allocation
If the `Memory Budget` only utilizes 10% of its allocation, the remaining tokens can dynamically roll over into the `Code Budget` to provide more file context.

However, if the `Code Budget` exceeds its limit, it is strictly truncated; it cannot steal tokens from the `System Budget`.
