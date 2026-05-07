# API Gateway

## Description
Central entry point for all microservices. Routes requests to appropriate services and implements JWT authentication filter.

## Port
8080

## Technology Stack
- Spring Boot 3.2.5
- Spring Cloud Gateway
- JWT Validation
- Lombok

## Routes Configuration

| Service | Route | Backend URL | Auth Required |
|---------|-------|-------------|---------------|
| Products | /products/** | http://localhost:8081 | No |
| Orders | /orders/** | http://localhost:8082 | Yes |
| Payments | /payments/** | http://localhost:8083 | Yes |
| Auth | /auth/** | http://localhost:8084 | No |

## Public Endpoints (No Token Required)
- POST /auth/login
- POST /auth/validate
- GET /products/**

## Protected Endpoints (Token Required)
- All /orders/** endpoints
- All /payments/** endpoints