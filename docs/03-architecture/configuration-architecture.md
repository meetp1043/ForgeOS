# Configuration Architecture

ForgeOS configuration is managed hierarchically to support distinct environments and multi-tenancy.

## Hierarchical Configuration
1. **Application Level (Spring Profiles)**: Defines infrastructure connection strings (DB, Redis) depending on the environment (`application-dev.yml`, `application-prod.yml`).
2. **Tenant Level (Database)**: Features flags, billing limits, and organizational rules specific to a tenant.
3. **Project Level (Database/Git)**: Specific `.forgeos` configuration files dictating preferred languages, linters, and architectural patterns.

## Secrets
Configuration containing secrets (e.g., Database passwords, API keys) must never be committed to Git. They are managed via AWS Secrets Manager or HashiCorp Vault and injected as environment variables at runtime.
