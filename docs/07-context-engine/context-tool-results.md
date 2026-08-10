# Tool Results

ForgeOS agents operate autonomously by executing tools. The output of these tools is a highly volatile, highly authoritative form of temporary context.

## Common Tool Outputs
- **Compiler**: Build errors and warnings.
- **Tests**: JUnit output, coverage reports.
- **Git**: Status, diffs, log outputs.
- **Docker**: Build logs, container statuses.
- **Database**: SQL query results, schema definitions.
- **Cloud**: AWS CLI output, Terraform plans.
- **Browser**: DOM snapshots, accessibility audit results.

## Managing Tool Result Context
Tool output can be massive. A single npm install error might generate 50,000 characters of text. 

To manage this, the Context Engine tracks tool results with:
- **Source**: Which tool generated it?
- **Timestamp**: Exact execution time.
- **Scope**: Which files or environments were targeted?
- **Risk**: Is this output from a read-only command or a destructive write?
- **Expiration**: Tool output expires very quickly (often immediately after the next tool is run or the code is modified).

Massive tool outputs must undergo [Compression](context-compression.md) (e.g., truncating the middle of a massive stack trace while keeping the error header and the specific failing frame).
