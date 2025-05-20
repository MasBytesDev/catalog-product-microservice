# 📦 Catalog Product Microservice

A Spring Boot microservice designed to manage product categories in a modular architecture. Part of a larger backend system for inventory and sales control.

## 🚀 Features

- Create, update, disable, enable and delete product categories
- Filter by status (`ACTIVE`, `INACTIVE`, `DELETED`)
- Pagination and filtering support
- Input validation and exception handling
- Unit testing using JUnit and Mockito

## ⚙️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- H2 (for testing)
- JUnit 5
- Mockito
- Maven

## 📁 Project Structure

```
catalog-product-microservice/
├── src/
│   ├── main/
│   │   ├── java/com/masbytes/catalog/
│   │   └── resources/
│   └── test/
├── .gitignore
├── pom.xml
├── README.md
```

## 🧪 Testing

Run tests with:

```bash
./mvnw test
```

## 🧙 Author

**Berto** – [@MasBytesDev](https://github.com/MasBytesDev)

---

### 📌 Notes

- This microservice is part of a learning process in backend development with Java and Spring.
- Contributions are welcome (PRs, suggestions, improvements).

---

## 🗺️ Roadmap

- [ ] Add integration tests
- [ ] Add Swagger/OpenAPI documentation
- [ ] Containerize with Docker
- [ ] Add service discovery (Eureka or similar)
