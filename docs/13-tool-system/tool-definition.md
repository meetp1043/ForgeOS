# Tool Definition

Each tool implements the `Tool` interface and advertises a `ToolDefinition`:

```java
public class ToolDefinition {
    private String id;
    private String name;
    private String version;
    private ToolCategory category;
    private ToolRiskLevel riskLevel;
    private ToolPermission requiredPermission;
}
```

The system uses this definition to decide if a given Tenant/Agent role possesses the necessary `ToolPermission`.
