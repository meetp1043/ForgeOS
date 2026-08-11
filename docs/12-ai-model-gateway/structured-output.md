# Advanced Capabilities

These capabilities are architected in the enums but fully implemented in future Agent modules.

## Tool Calling
Spring AI handles JSON Schema generation. The ForgeOS `ModelGateway` abstracts this so agents can register `FunctionCallback`s dynamically.

## Structured Output
Agents can ask for strict JSON mapping. The Gateway uses Spring AI's `BeanOutputConverter`.

## Streaming
Future UI requires live token streams. The `ModelProvider` will be extended with `Flux<String> executeStream(ModelRequest)`.
