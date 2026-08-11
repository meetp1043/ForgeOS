# Provider Abstraction

ForgeOS isolates provider SDKs behind the `ModelProvider` interface.

```java
public interface ModelProvider {
    String getProviderName();
    boolean isAvailable();
    Set<ModelCapability> getSupportedCapabilities();
    ModelPrivacyClassification getMaxAllowedPrivacy();
    ModelResponse execute(ModelRequest request) throws ModelGatewayException;
}
```

This prevents `ChatClient` from leaking into domain entities.
