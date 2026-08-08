# Security Baseline

- **Data Protection**: Encrypt data at rest and in transit.
- **Authentication**: All endpoints (except public web assets) must require authentication.
- **Authorization**: Enforce role-based or attribute-based access control.
- **Input Validation**: Never trust user (or AI) input. Validate strictly on the backend.
- **Dependencies**: Keep dependencies updated and regularly scan for vulnerabilities.
- **Secrets**: Scan repository to ensure no secrets are pushed.
