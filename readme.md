# Order Service

A microservice for order management built with Spring Boot, implementing CQRS pattern for scalable and maintainable order processing.

## Overview

This service provides order management capabilities with separate command and query models, ensuring high performance and scalability for both read and write operations.

## Tech Stack

- **Framework**: Spring Boot 3.4.5
- **Language**: Java 18
- **Database**: H2 (In-memory)
- **Build Tool**: Maven 3.9.9
- **Pattern**: CQRS (Command Query Responsibility Segregation)

## Core Components

### Command Side
- Order creation and management
- Domain events handling
- Command validation and processing

### Query Side
- Order information retrieval
- Optimized read models
- Real-time order status updates

## Quick Start

1. **Build the Project**
```bash
./mvnw clean install
```

2. **Run the Application**
```bash
./mvnw spring-boot:run
```

The service will be available at `http://localhost:8080`

## API Documentation

### Create Order
```http
POST /orders
Content-Type: application/json
Body:
{
  "customerId": "user-123",
  "items": [
    { "productId": "prod-1", "quantity": 2, "price": 100.0 },
    { "productId": "prod-2", "quantity": 1, "price": 200.0 }
  ]
}
Response: "order ID（sample:8a9d7f6e-5c4b-3a2b-1e0f-9d8c7b6a5e4f）"


### Get Order Details
```http
GET /orders/8a9d7f6e-5c4b-3a2b-1e0f-9d8c7b6a5e4f
Response:
{
  "orderId": "8a9d7f6e-5c4b-3a2b-1e0f-9d8c7b6a5e4f",
  "customerId": "user-123",
  "status": "CREATED",
  "items": [
    { "productId": "prod-1", "quantity": 2, "price": 100.0 },
    { "productId": "prod-2", "quantity": 1, "price": 200.0 }
  ]
}


## Project Structure

```
src/main/java/com/example/order/
├── command/                            # Command handling
│   ├── application/                    # Command services
│   ├── domain/                         # Domain models
│   └── infrastructure/                 # Command persistence
│       ├── OrderRepository.java        # JPA repository for order aggregates
│       └── eventListener/              # Event listeners
│           └── OrderEventListener.java # Listens for order events and updates read model
├── query/                              # Query handling
│   ├── application/                    # Query services
│   ├── model/                          # Query models
│   └── infrastructure/                 # Query persistence
│       └── OrderViewRepository.java    # JPA repository for order view models
└── controller/                         # REST endpoints
```

## Key Features

- **CQRS Implementation**: Separate models for commands and queries
- **Event-Driven Architecture**: Domain events for system consistency
- **JPA Integration**: Efficient data persistence
- **RESTful API**: Clean and intuitive endpoints
- **In-Memory Database**: Fast development and testing

## Configuration

The application uses the following default configuration (application.yml):

```yaml
spring:
  application:
    name: order
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## Development

### Prerequisites
- JDK 18
- Maven 3.9.9+

### Building
```bash
./mvnw clean install
```

### Testing
```bash
./mvnw test
```

## Architecture

### Command Flow
1. REST endpoint receives command
2. Command service processes request
3. Domain model validates and executes
4. Event published for query model update

### Query Flow
1. REST endpoint receives query
2. Query service retrieves data
3. Optimized read model returns result

## Order States

Orders can be in the following states:
- CREATED
- PAID
- CANCELLED

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

MIT License

Copyright (c) 2025 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
