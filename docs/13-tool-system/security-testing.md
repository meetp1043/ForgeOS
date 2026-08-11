# Security Testing

The tool system must be constantly tested against hallucinated or malicious AI intents.

`PathTraversalTests.java` demonstrates that an AI requesting to read `/etc/passwd` via `fs_read` is immediately trapped and blocked by the `ToolValidationException`.

`ToolAuthorizationTests.java` demonstrates that any tool marked as `CRITICAL` will throw `ToolAuthorizationException` because the isolation container runtime (Phase 19) is not yet active.
