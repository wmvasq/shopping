# Orders Service

## Description
Manages shopping cart orders. Validates product information by calling Products Service via Feign. Calculates order totals automatically.

## Port
8082

## Technology Stack
- Spring Boot 3.5.14
- Spring Data JPA
- H2 Database
- OpenFeign
- Lombok

## Database
H2 in-memory database. Console available at: `http://localhost:8082/h2-console`

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /orders | Create new order |
| GET | /orders | Get all orders |
| GET | /orders/{id} | Get order by ID |
| GET | /orders/client/{clientId} | Get orders by client |

## Create Order Request Example

```json
{
    "clientId": 1,
    "items": [
        {
            "productId": 1,
            "quantity": 2
        }
    ]
}