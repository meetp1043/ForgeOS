# Context Prioritization

Prioritization defines the criticality of a Context Item. It dictates how the Context Engine behaves when facing strict token budgets.

## Priority Tiers

1. **CRITICAL**: The agent's task is fundamentally impossible or dangerous without this context. 
   - *Examples*: Security boundaries, output schemas, immediate task objectives.
   - *Action on Budget Limit*: **Block execution**. Do not silently drop critical context.

2. **HIGH**: Strongly required for a correct implementation.
   - *Examples*: Relevant ADRs, immediate API contracts, adjacent source code.
   - *Action on Budget Limit*: Attempt aggressive summarization or request a larger-context model.

3. **NORMAL**: Useful context that provides necessary background.
   - *Examples*: Test suites for adjacent modules, project coding conventions.
   - *Action on Budget Limit*: Compress aggressively, convert to references.

4. **LOW**: Tangentially related information.
   - *Examples*: Old conversation summaries, historical bug reports on similar features.
   - *Action on Budget Limit*: Truncate.

5. **OPTIONAL**: Information that might provide a slight edge in reasoning but is mostly noise.
   - *Examples*: Broad web search results.
   - *Action on Budget Limit*: Drop entirely.

## Resolution Strategies for CRITICAL Overflow
If `CRITICAL` context cannot fit within the model budget, the Context Engine must execute one of the following fallbacks:
- Compress the critical context (while verifying meaning is preserved).
- Drop all `HIGH`, `NORMAL`, `LOW`, and `OPTIONAL` context to make room.
- Request the Model Router to escalate to a model with a larger context window.
- Inform the Workflow Engine that the task is too large and must be split.
- **Fail Closed** and alert the human operator.
