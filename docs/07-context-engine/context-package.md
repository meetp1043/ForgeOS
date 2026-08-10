# Context Package

The Context Package is the final, assembled payload delivered by the Context Engine to the Agent Runtime. It represents the absolute state of knowledge the agent possesses for a given invocation.

## Structure of a Context Package

The package is structurally divided to prevent prompt injection and model confusion. It typically contains:

1. **System Instructions**: Immutable directives provided by the ForgeOS framework (e.g., output formatting, constraints).
2. **Agent Role Instructions**: Directives specific to the agent's persona.
3. **Task Instructions**: The immediate objective and acceptance criteria.
4. **Project Constraints**: High-level rules (e.g., "Do not use external libraries").
5. **Approved Architecture**: Relevant ADRs and design documents.
6. **Relevant Source Code**: Precisely targeted files or code snippets.
7. **Relevant Tests**: Unit or integration tests related to the modified code.
8. **Relevant Documentation**: APIs, SDK docs, or READMEs.
9. **Relevant Memory**: Factual, distilled knowledge from the Memory Engine.
10. **Previous Execution Information**: History of failures or previous agent turns on this exact task.
11. **Git State**: Current branch, uncommitted changes, or relevant diffs.
12. **Tool Results**: The raw output of a recently executed compiler, linter, or shell command.
13. **Security Constraints**: Explicit boundaries for the current operation.

## Provenance Requirement
Every item within the Context Package must have an attached **provenance tag**. The agent (and the human auditor) must be able to trace exactly where a piece of information came from (e.g., `[Source: ProjectMemory(ID: 402)]` or `[Source: Git(Branch: main, File: src/main.js)]`).
