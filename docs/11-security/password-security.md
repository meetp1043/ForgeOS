# Password Security

## Hashing
We use **BCrypt** via Spring Security's `BCryptPasswordEncoder`. 
- Work factor is default (currently 10), which provides a good balance between brute-force resistance and server load.
- Passwords are never logged.

## Storage
Passwords are stored in `users.password_hash`. Raw passwords never leave the `AuthService` context.

## Lockout
The `users` table includes `failed_login_attempts` and `locked_until`. While the initial implementation does not fully activate rate-limiting, the schema is prepared for progressive lockout delays (e.g., locking an account for 15 minutes after 5 failed attempts).
