# Token Strategy

## Access Token (JWT)
- **Format**: Signed JWT (HMAC-SHA256).
- **Claims**:
  - `sub`: User email
  - `userId`: User UUID
  - `iat`: Issued At
  - `exp`: Expiration
- **Exclusions**: Passwords, permissions list (too large), tenant data (managed securely in headers/DB instead).

## Refresh Token
- **Format**: Secure random UUID stored in the database.
- **Security**: Only transmitted over HTTPS. It is effectively a long-lived credential and must be guarded carefully by the client.
