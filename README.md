# Shopping Cart Microservices

## Services

| Service | Port | Description |
|---------|------|-------------|
| API Gateway | 8080 | Routing and JWT authentication |
| Products Service | 8081 | Proxy to fakestoreapi.com |
| Orders Service | 8082 | Order management |
| Payment Service | 8083 | Payment simulation |
| Auth Service | 8084 | JWT token generation |

## Quick Start

### 1. Start Services (in separate terminals)

```bash

# Auth Service
cd auth-service && mvn spring-boot:run

# Products Service
cd products-service && mvn spring-boot:run

# Orders Service
cd orders-service && mvn spring-boot:run

# Payment Service
cd payment-service && mvn spring-boot:run

# API Gateway
cd api-gateway && mvn spring-boot:run