# Authentication Architecture

ForgeOS uses **JWT Bearer Tokens** for stateless authentication on all APIs.

## Flow
1. User calls `/api/auth/login` with email/password.
2. `AuthService` verifies against BCrypt hash in PostgreSQL.
3. `JwtService` issues an Access Token (15 mins) and a Refresh Token (7 days).
4. Subsequent API calls include `Authorization: Bearer <token>`.
5. `JwtAuthenticationFilter` validates the signature, extracts the email, and populates `SecurityContextHolder`.

## Endpoints
- `POST /api/auth/register`: Create a new user (active immediately, email unverified).
- `POST /api/auth/login`: Issue tokens.
- `POST /api/auth/refresh`: Issue new Access Token using a valid Refresh Token.
