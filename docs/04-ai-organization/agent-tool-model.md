# Agent Tool Model

Tools are the mechanism by which agents interact with the environment. ForgeOS does not implement the tools yet, but defines their conceptual categories and constraints.

## Tool Categories

- **File Tools**: `read_file`, `write_file`, `list_directory`, `search_regex`.
- **Git Tools**: `git_status`, `git_diff`, `git_commit`, `git_branch`.
- **GitHub Tools**: `create_pr`, `review_pr`, `comment_pr`.
- **Terminal Tools**: `run_command` (restricted bash execution in Sandbox).
- **Build Tools**: Specific wrappers around `mvn`, `npm`, `gradle`.
- **Test Tools**: `run_tests`, `run_linter`.
- **Database Tools**: `execute_query`, `generate_migration`.
- **Cloud Tools**: `aws_cli`, `terraform_apply`.
- **Browser Tools**: `navigate`, `click`, `extract_dom` (for E2E UI testing).
- **AI Tools**: `ask_subagent` (delegation), `extract_structured_data`.
- **Documentation Tools**: `generate_openapi`, `update_changelog`.

## Required Tool Metadata
Every tool definition must include:
- **Identity**: Unique name.
- **Purpose**: Clear description of what it does.
- **Input Schema**: JSON Schema of expected arguments.
- **Output Schema**: JSON Schema of the return value.
- **Permissions**: The RBAC role required to use it.
- **Timeout**: Maximum execution time (e.g., 5000ms).
- **Audit**: Boolean indicating if executions must be permanently logged.
- **Risk Level**: LOW, MEDIUM, HIGH, CRITICAL. (High/Critical tools may require human approval before execution).
