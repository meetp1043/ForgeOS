# Integration Architecture

ForgeOS integrates with external systems via defined boundary interfaces.

## External VCS (GitHub / GitLab)
- **Inbound**: Webhooks notify ForgeOS of PR comments, merges, or pushes to tracked branches, triggering workflow resumes.
- **Outbound**: Agents use standard Git CLI tools (via the Sandbox) or REST APIs to clone, branch, commit, and open PRs. Authentication uses short-lived OAuth tokens or GitHub App installations, never hardcoded personal access tokens.

## Cloud Providers (AWS / GCP / Azure)
- **Deployment**: DevOps agents use Terraform/CDK or direct CLIs (in the Sandbox) to provision infrastructure.
- **Credentials**: Temporary STS credentials (AWS) are generated and injected into the sandbox just-in-time, avoiding long-lived IAM keys.

## LLM Providers
- Interfaced via `Spring AI`. The Model Router handles retries, rate-limit backoffs (429s), and failovers using Spring Retry.
