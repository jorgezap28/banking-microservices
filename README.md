This project is a backend solution for a banking system developed as part of a technical exercise. It is built using Java 17 and Spring Boot, following clean architecture principles and microservices design.

## Technologies Used

- Java 17+
- Spring Boot (latest stable version)
- Spring WebFlux
- JPA (Java Persistence API)
- Lombok
- Docker
- OpenAPI
- Postman 
- Relational Database

## Project Structure

The system is divided into two microservices:

1. **Customer Service**: Manages `Person` and `Customer` entities.
2. **Account Service**: Manages `Account` and `Movement` entities.

Communication between microservices is handled via Kafka.

## Main Functionalities

- **F1**: CRUD operations for Customer, Account, and Movement.
  - Endpoints:
    - `/api/v1/customers`
    - `/api/v1/accounts`
    - `/api/v1/movements`
- **F2**: Movement registration with validation:
  - Debit subtracts from balance
  - Credit adds to balance
  - Value must be > 0
- **F3**: Error handling for insufficient balance (`"Saldo no disponible"`).
- **F4**: Account statement report by date range and client.
  - Endpoint: `/reports/{client-id}?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`
  - Output: JSON or Excel
- **F5**: Unit tests for Movement service.
- **F6**: Integration test (minimum one).
- **F7**: Docker-based deployment.

##  Domain Entities

- **Person**: `name`, `gender`, `identification`, `address`, `phone`
- **Customer** (inherits Person): `password`, `status`
- **Account**: `number`, `type`, `initialBalance`, `status`
- **Movement**: `date`, `type`, `value`, `balance`



## Deployment Instructions

- Generate database schema and entities in `banking.sql`
- Build and run services using Docker
- Provide OpenAPI specification (`.yaml`)
- Share endpoint test collection (Postman)



