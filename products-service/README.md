# Products Service

## Description
Proxy service that acts as an intermediary between the client and the external FakeStore API. Provides product information for the shopping cart.

## Port
8081

## Technology Stack
- Spring Boot 4.0.6
- OpenFeign
- Lombok

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /products | Get all products |
| GET | /products/{id} | Get product by ID |

## External API
This service proxies requests to: `https://fakestoreapi.com`

## Running

```bash
cd products-service
mvn spring-boot:run