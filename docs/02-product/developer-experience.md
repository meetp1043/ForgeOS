# Developer Experience

While non-technical users interact mostly via the dashboard and conversation, professional developers require deep, low-level access.

## Developer Features

- **Repository Access**: Developers have direct Git access to clone, branch, and push code manually.
- **Branch Workflow**: AI agents work on isolated branches. Human developers can checkout these branches locally to inspect or modify the code before merging.
- **Code Review**: A robust diff viewer allowing line-by-line commenting on AI-generated pull requests.
- **Logs**: Access to raw execution logs (stdout/stderr) for builds, tests, and agent tool executions.
- **Terminal**: An in-browser terminal connected to the workspace for manual commands.
- **IDE Integration**: Future support for VS Code / IntelliJ plugins to interact with ForgeOS agents directly within the human's local editor.
- **Pull Requests**: Standard PR workflows where human developers and AI Reviewers co-author approvals.
- **Debugging**: The ability to pause an agent and inspect the workspace state mid-execution to debug why an agent is failing.
- **Architecture Inspection**: Direct access to the internal context graph/ADRs to see exactly why the system is constrained.
- **Agent Control**: The ability to manually override an agent's assigned task or forcibly reset its context window.
