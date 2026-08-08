# API Architecture

The ForgeOS API layer is the boundary between the frontend clients and the Spring Modulith core.

## API Standards
- **Protocol**: REST over HTTPS for standard CRUD operations. WebSockets for real-time agent output streaming.
- **Data Format**: JSON (application/json).
- **Versioning**: URI versioning (e.g., `/api/v1/projects`).
- **Documentation**: OpenAPI 3.0 specs generated automatically via Springdoc.

## Real-Time Streaming
Because AI agent execution can take minutes, the API cannot rely on synchronous blocking calls.
1. Client POSTs a command (`/api/v1/projects/{id}/execute`).
2. Server returns `202 Accepted` with a `taskId`.
3. Client subscribes to a WebSocket channel (`/ws/projects/{id}`).
4. Server pushes Server-Sent Events (SSE) or WebSocket messages containing agent logs, tool outputs, and status transitions in real-time.
