# Catalog Product Microservice

This microservice is responsible for managing product categories in an e-commerce system. It is built with Java and Spring Boot, following best practices for backend development and clean architecture.

## Features

- CRUD operations for product categories
- Soft delete functionality with status management
- Pagination and filtering support
- Detailed validation and exception handling

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- Maven
- JUnit 5 & Mockito for testing

## Project Structure

```
catalog-product-microservice
│
├── src/main/java/com/example/catalog
│   ├── controller          # REST controllers
│   ├── dto                 # Data transfer objects
│   ├── entity              # JPA entities
│   ├── exception           # Custom exceptions
│   ├── mapper              # Mappers for DTOs and entities
│   ├── repository          # Spring Data JPA repositories
│   ├── service             # Service interfaces and implementations
│   └── validator           # Validation utilities
│
├── src/test/java/com/example/catalog
│   └── service             # Unit and integration tests for services
│
├── src/main/resources
│   ├── application.yml     # Application configuration
│   └── db                  # Database scripts
│
├── .gitignore
├── pom.xml
└── README.md
```

## How to Run

1. Clone the repository
2. Build the project using Maven: `mvn clean install`
3. Run the Spring Boot application: `mvn spring-boot:run`

## Contribution

Feel free to fork and submit pull requests. Please follow the code style and include tests for new features.

## License

MIT License
