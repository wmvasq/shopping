```markdown
# Auth Service

## Description
Provides JWT-based authentication and authorization. Generates tokens for authenticated users.

## Port
8084

## Technology Stack
- Spring Boot 3.5.14
- Spring Security
- JWT (jjwt 0.12.5)
- Lombok

## Default Users (Hardcoded)

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/login | Authenticate and get JWT token |
| POST | /auth/validate | Validate JWT token |

## JWT Configuration
- Secret: Configured in application.properties
- Expiration: 24 hours (86400000 ms)

## Login Request Example

```json
{
    "username": "admin",
    "password": "admin123"
}