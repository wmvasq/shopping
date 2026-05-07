# Payment Service

## Description
Simulates payment processing. Validates order amounts by consulting Orders Service via Feign. 

## Port
8083

## Technology Stack
- Spring Boot 3.5.14
- Spring Data JPA
- H2 Database
- OpenFeign
- Lombok

## Database
H2 in-memory database. Console available at: `http://localhost:8083/h2-console`

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /payments | Process payment |
| GET | /payments | Get all payments |
| GET | /payments/order/{orderId} | Get payment by order ID |

## Payment Methods
- CREDIT_CARD
- DEBIT_CARD
- PAYPAL
- BANK_TRANSFER

## Payment Status
- APPROVED
- REJECTED

## Process Payment Request Example

```json
{
    "orderId": 1,
    "amount": 219.90,
    "method": "CREDIT_CARD"
}