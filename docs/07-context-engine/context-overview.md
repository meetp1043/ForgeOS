# Context Engine Overview

## What is the Context Engine?
The Context Engine is the sub-system responsible for constructing the **Context Package**—the exact, minimal, secure, and relevant information payload injected into an AI agent's execution prompt. 

While ForgeOS may hold terabytes of code, millions of memories, and thousands of architecture documents, an individual agent cannot and should not see all of this at once. The Context Engine acts as the intelligent filter and assembler.

## Primary Responsibilities
1. **Understand the Request**: Interpret the current task, agent role, and workflow state.
2. **Discover Candidates**: Query the Memory Engine, Git repositories, Artifact stores, and current Tool Outputs for potentially relevant information.
3. **Filter for Security**: Ensure no unauthorized secrets, cross-tenant data, or restricted artifacts enter the prompt.
4. **Rank and Compress**: Prioritize the highest-authority, most relevant data and compress it to fit within the designated token budget.
5. **Assemble**: Format the data distinctly from instructions to prevent prompt injection.

## What It Does NOT Do
To maintain clear architectural boundaries, the Context Engine explicitly avoids taking on responsibilities of other systems:

- **It does NOT permanently store memory.** (That is the Memory Engine's job).
- **It does NOT execute agents.** (That is the Agent Runtime's job).
- **It does NOT execute tools.** (That is the Tool System's job).
- **It does NOT own workflows.** (That is the Workflow Engine's job).
- **It does NOT make product decisions.** (Context simply supplies the approved ADRs to the agent; the agent or human makes the decision).
- **It does NOT replace the model.** (It prepares data *for* the model).
- **It does NOT replace the agent runtime.** (It is invoked *by* the runtime).

Its sole, critical responsibility is **Context Construction**.
