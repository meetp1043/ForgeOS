# Tool Safety

Some tools are inherently dangerous and require strict safety controls beyond standard permissions. The Tool System classifies tools by risk, and the Agent Framework enforces these controls.

## High-Risk / Dangerous Tools

The following operations are conceptually flagged as dangerous:
- **Database Deletion**: `DROP`, `TRUNCATE`, `DELETE` without a `WHERE` clause.
- **Production Deployment**: Pushing code to live servers.
- **Cloud Resource Deletion**: Terminating EC2 instances, deleting S3 buckets.
- **Credential Modification**: Rotating database passwords, generating new AWS keys.
- **Data Deletion**: Hard-deleting user records.
- **Production Configuration**: Changing live DNS records or load balancer settings.

## Safety Controls

1. **Explicit Whitelisting**: An agent must have the explicit permission (e.g., `DATA_DELETE`) to even see the tool in its schema.
2. **Mandatory Approval**: Calling a dangerous tool forces the Agent Instance into the `WAITING_FOR_APPROVAL` state. The execution halts until a Human or a Manager Agent (with sufficient authority) signs off on the exact payload the tool intends to execute.
3. **Dry Runs**: Where possible (e.g., Terraform), the framework forces the tool to execute in a "plan" or "dry-run" mode first, and presents the diff to the approver.
4. **Sandboxing**: Dangerous CLI tools execute in ephemeral, heavily locked-down network namespaces to prevent lateral movement.
