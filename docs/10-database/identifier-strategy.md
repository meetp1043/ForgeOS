# Identifier Strategy

All primary keys in the ForgeOS relational database use **UUIDs**.

## Why UUIDs?
- **Global Uniqueness**: Allows records to be generated safely across disconnected systems or nodes without sequence collisions.
- **Security**: Prevents ID enumeration attacks (e.g., iterating from `/projects/1` to `/projects/2`).
- **Future-proofing**: As the system scales to potential microservices, a shared UUID context (e.g., the `tenant_id` string) remains consistently traceable.

We utilize `java.util.UUID.randomUUID()` in the Java `BaseEntity`, mapped to the native `uuid` column type in PostgreSQL.
