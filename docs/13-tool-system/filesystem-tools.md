# Filesystem Tools

Filesystem access is the most immediate vector for host compromise.

## Protections
All tools (`fs_read`, `fs_write`, `fs_list`) use Java's `.toRealPath()` and `.normalize()` to canonicalize the requested path.

Before executing, the tool validates:
`targetPath.startsWith(workspaceRoot)`

If an AI attempts to read `../../../../etc/passwd`, the canonical path will not start with the Workspace Root, and the tool will throw a `ToolValidationException`.
