# Merge Gates & Evaluation

The `MergeGateEvaluator` ensures that no code reaches production branches without satisfying security and quality requirements.

## Evaluation Rules
1. **CI Passing**: Must be `true`.
2. **Security Passing**: Must be `true`.
3. **Protected Branches**: If the `GitOperationPolicy` defines the target branch as protected (e.g., `main`, `master`), the evaluator strictly requires `HumanApproval == true`. 

If any of these conditions fail, the evaluator returns `MergeGateStatus.BLOCKED`, and the `GitHubAdapter` will refuse to execute the merge operation.
