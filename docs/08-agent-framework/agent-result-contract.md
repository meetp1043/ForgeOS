# Result Contract

Every agent execution must adhere to a strict JSON schema for its final output. This ensures downstream systems (and other agents) can programmatically parse the outcome.

## Standard JSON Schema

```json
{
  "execution_id": "exec_9a8b7c6d",
  "agent_version": "backend-engineer:v2.1",
  "status": "SUCCESS | FAILED | ESCALATED",
  "summary": "Implemented the /api/orders POST endpoint.",
  "actions_taken": [
    "Created OrderController.java",
    "Updated OrderService.java",
    "Executed Maven Test"
  ],
  "files_changed": [
    "src/main/java/com/forgeos/OrderController.java",
    "src/main/java/com/forgeos/OrderService.java"
  ],
  "artifacts": [
    "link-to-openapi-spec-diff"
  ],
  "evidence": {
    "tests_passed": 42,
    "tests_failed": 0,
    "build_status": "SUCCESS",
    "logs": "..."
  },
  "warnings": [
    "Database query might be slow at high volume, consider adding an index in a future task."
  ],
  "errors": [],
  "recommendations": [
    "Frontend needs to update their API client to match the new schema."
  ],
  "confidence": 0.95,
  "unresolved_questions": [],
  "metrics": {
    "cost_usd": 0.42,
    "duration_ms": 45000,
    "model_used": "gpt-4o"
  }
}
```

The Framework enforces this contract. If an agent outputs malformed JSON or omits mandatory fields, the framework handles it as a `MODEL_ERROR`.
