# Error Handling

ForgeOS implements a global exception handling strategy using Spring's `@ControllerAdvice` or `HandlerExceptionResolver`.

## Exception Taxonomy
- `ForgeOSException`: The base runtime exception.
- `ValidationException` -> Maps to HTTP 400 Bad Request.
- `UnauthorizedException` -> Maps to HTTP 401 Unauthorized.
- `ForbiddenException` -> Maps to HTTP 403 Forbidden.
- `ResourceNotFoundException` -> Maps to HTTP 404 Not Found.
- `ConflictException` -> Maps to HTTP 409 Conflict.

## Client Responses
Errors returned to API clients or other agents must follow a standard JSON structure:
```json
{
  "timestamp": "2024-05-10T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Agent Definition 'backend-engineer' not found.",
  "traceId": "abc-123"
}
```
Stack traces are explicitly stripped from production API responses.
