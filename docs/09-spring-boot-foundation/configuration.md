# Configuration Strategy

ForgeOS uses environment-based configuration via Spring Boot profiles.

## Files
- `application.yml`: Base configuration (logging levels, actuator settings).
- `application-dev.yml`: Local development settings (localhost database URLs, auto-DDL).
- `application-prod.yml` (Future): Production settings with strict SSL and connection pooling.

## Secrets Management
**Never hardcode secrets in YAML files.**
All credentials must use environment variable substitution:
```yaml
password: ${DB_PASSWORD}
```

In development, a fallback is acceptable: `${DB_PASSWORD:forgeos_dev_secret}`, but production environments will inject these via Kubernetes Secrets or AWS Secrets Manager.
