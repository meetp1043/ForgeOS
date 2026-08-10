# Agent Tool Matrix

This matrix maps specific JSON tool schemas to roles.

| Tool Name | Backend Eng | Frontend Eng | DBA | QA Eng | Sec Eng | Sol Arch | DevOps |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `execute_maven` | Yes | No | No | Yes | No | No | Yes |
| `execute_npm` | No | Yes | No | Yes | No | No | Yes |
| `execute_sql` | No | No | Yes | No | No | No | No |
| `run_playwright` | No | Yes | No | Yes | No | No | No |
| `run_sonarqube` | No | No | No | No | Yes | No | Yes |
| `aws_cli` | No | No | No | No | No | No | Yes |
| `kubectl` | No | No | No | No | No | No | Yes |
| `git_diff` | Yes | Yes | Yes | Yes | Yes | Yes | Yes |
| `create_adr` | No | No | No | No | No | Yes | No |
| `search_jira` | Yes | Yes | Yes | Yes | Yes | Yes | Yes |
