# 📦 Catalog Product Microservice

Este microservicio es responsable de gestionar las categorías de productos en un sistema de facturación e inventario. Está construido con Java y Spring Boot, siguiendo las mejores prácticas de desarrollo backend y una arquitectura limpia.

---

## 🚀 Características

- Operaciones CRUD para categorías de productos
- Eliminación lógica con gestión de estado
- Paginación y soporte de filtrado
- Validación detallada y manejo de excepciones

---

## 🛠️ Tecnologías Utilizadas

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- Maven
- JUnit 5 & Mockito para pruebas

---

## 📁 Estructura del Proyecto

```bash
catalog-product-microservice/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── catalog/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               └── service/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── catalog/
├── pom.xml
└── README.md
```

---

## ⚙️ Configuración y Ejecución

### Prerrequisitos

- Java 21
- Maven 3.8+

### Pasos para Ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/MasBytesDev/catalog-product-microservice.git
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd catalog-product-microservice
   ```

3. Construye el proyecto:
   ```bash
   mvn clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---


## 🧪 Pruebas

El proyecto incluye pruebas unitarias utilizando JUnit 5 y Mockito. Para ejecutar las pruebas:

```bash
mvn test
```

---

## 📌 Notas Adicionales

- La aplicación utiliza una base de datos en memoria H2 para facilitar las pruebas y el desarrollo.
- Se recomienda configurar una base de datos persistente para entornos de producción.

---

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b feature/nueva-funcionalidad`
3. Realiza tus cambios y haz commits: `git commit -m 'Agrega nueva funcionalidad'`
4. Envía tus cambios al repositorio remoto: `git push origin feature/nueva-funcionalidad`
5. Abre un Pull Request.

---

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT.

---

## 📬 Contacto

Para consultas o sugerencias, por favor contacta a [MasBytesDev](mailto:dev.bertosv@gmail.com).
