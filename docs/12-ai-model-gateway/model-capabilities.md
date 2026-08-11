# Model Capabilities

Each provider advertises what it can do via `ModelCapability`:

- `CHAT`: Basic text generation.
- `STRUCTURED_OUTPUT`: Can return JSON mapped to classes.
- `TOOL_CALLING`: Can invoke function signatures.
- `EMBEDDINGS`: Can generate vectors.
- `VISION`: Image understanding.
- `STREAMING`: Streaming tokens.

The Router uses these to filter out providers that cannot fulfill the request.
