# Code Integration

Injecting source code into an agent's prompt is the most token-intensive operation in ForgeOS. The Context Engine must utilize progressive retrieval to prevent context overflow.

## The "No Entire Repositories" Rule
**Do NOT send entire repositories by default.** 
It is almost always a catastrophic waste of tokens and degrades the model's ability to focus on the specific file requiring edits.

## Progressive Retrieval Sequence
The Context Engine retrieves code in expanding concentric circles of relevance based on the Task:

1. **Task Target**: The specific file(s) mentioned in the task description.
2. **Relevant Module**: The directory immediately containing the target files.
3. **Relevant Symbols**: The specific functions, classes, or interfaces referenced within the target file.
4. **Dependencies**: Code from files imported by the target file (often just the interface/signatures, not the full implementation).
5. **Tests**: The unit tests directly corresponding to the target file.
6. **Configuration**: Relevant `pom.xml`, `package.json`, or `.env.example` files if dependency changes are required.
7. **Related Interfaces**: Code that implements or consumes the interfaces modified by the task.

Through aggressive [Compression](context-compression.md), non-target files are often reduced to just their function signatures rather than their full AST.
