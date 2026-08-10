# Workflow Sequential Execution

Sequential execution ensures that steps run in strict order, each starting only after its predecessor completes.

## Behavior
A `SEQUENTIAL` container step executes its children one at a time, in the declared order. The output of step N is available as input context for step N+1.

## Use Cases
- **Code Review after Implementation**: The Code Review Engineer cannot review code that hasn't been written yet.
- **Testing after Code Review**: QA runs tests only on reviewed, approved code.
- **Deployment after Testing**: Deployment proceeds only when the test suite passes.

## Failure Behavior
If any child step in a sequential chain fails:
1. The failing step's retry policy is applied.
2. If retries are exhausted, subsequent steps are marked `SKIPPED`.
3. The sequential container transitions to `FAILED`.
4. Compensation logic is triggered in reverse order for any previously completed children.
