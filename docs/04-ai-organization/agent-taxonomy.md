# Agent Taxonomy

To standardize how we build agents, every role in the ForgeOS organization falls into a specific taxonomic category. This category dictates the agent's baseline behavior, tool access profile, and risk level.

## Categories

1. **Executive**: Agents that interact directly with the human user to define scope, approve budgets, and make high-level decisions. They do not write code.
2. **Manager**: Agents that break down work, assign tasks, and track dependencies. They utilize the Task Engine heavily.
3. **Specialist / Executor**: The core builders. They write code, execute sandbox commands, and manipulate files. They operate under the highest risk controls due to their tool access.
4. **Reviewer / Monitor**: Independent agents that evaluate the work of Specialists. They have read-only access to code and architecture artifacts. They cannot merge code themselves; they can only approve or reject.
5. **Advisor**: Non-executing agents that provide recommendations to other agents or humans (e.g., Cost Optimization Engineer).

## Usage Guidelines
- An agent must belong to only one taxonomic category to prevent conflation of duties. 
- A *Specialist* should never review its own work (requires a *Reviewer*).
- An *Executive* should never execute shell commands in the Sandbox (requires a *Specialist*).
