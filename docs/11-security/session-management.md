# Session Management

Because ForgeOS uses JWT, we do not have traditional JSESSIONID cookies.

## Access vs Session
- The `Access Token` is short-lived (15 minutes). It is fully stateless and cannot be revoked instantly without complex distributed blacklists.
- The `Refresh Token` is stateful. It is stored in the `refresh_tokens` table.

## Revocation
To forcefully end a user's session (e.g., if their account is suspended, or they click "Logout all devices"):
1. We set `revoked = true` in the `refresh_tokens` table for their tokens.
2. Once their current 15-minute access token expires, they will be unable to refresh, effectively logging them out.
