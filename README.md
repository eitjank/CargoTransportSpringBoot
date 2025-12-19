# CargoTransportSpring

A simple Spring Boot backend for managing cargos, users, trucks, routes, checkpoints and a small forum.

## Summary

CargoTransportSpring is a RESTful Spring Boot application (Java 17) that provides CRUD endpoints for the main domain models used in a cargo transportation scenario.

## Tech stack

- Java 17
- Spring Boot 2.7.5
- Spring Data JPA
- Spring Web
- Spring HATEOAS
- MySQL (production/dev configuration in `src/main/resources/application.properties`)
- Maven (wrapper provided)

Dependencies are declared in `pom.xml`.

## Prerequisites

- Java 17 JDK installed and JAVA_HOME configured
- Git (optional)
- Maven wrapper is included (`mvnw.cmd`) so you can run builds without installing Maven globally
- MySQL running (or change to any other datasource)

## Configuration (database)

Default DB settings are in `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/CargoTransportation
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform = org.hibernate.dialect.MySQL5Dialect
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

Change the URL, username, and password for your environment. Do not commit secrets.

## Build & run (Windows PowerShell)

From project root (where `mvnw.cmd` lives):

```powershell
# build
.\mvnw.cmd package

# run the application
.\mvnw.cmd spring-boot:run
```

The app starts on port 8080 by default.

## API Endpoints

All endpoints are JSON-based. Base path for controllers: `/cargo`, `/users`, `/trucks`, `/routes`, `/checkpoints`, `/comments`, `/forums`.

Summary of endpoints (method, path):

- Cargo (`/cargo`)
  - GET  /cargo/getAll
  - GET  /cargo/get/{id}
  - POST /cargo/add
  - DELETE /cargo/delete/{id}
  - PUT  /cargo/update/{id}

- Users (`/users`)
  - GET  /users/getAll
  - GET  /users/get/{id}
  - POST /users/validateUser    (expects JSON with `login` and `password` properties)
  - POST /users/addDriver
  - POST /users/addManager
  - DELETE /users/delete/{id}
  - PUT  /users/updateManager/{id}
  - PUT  /users/updateDriver/{id}

- Trucks (`/trucks`)
  - GET  /trucks/getAll
  - GET  /trucks/get/{id}
  - POST /trucks/add
  - DELETE /trucks/delete/{id}
  - PUT  /trucks/update/{id}

- Routes (`/routes`)
  - GET  /routes/getAll
  - GET  /routes/getAllByManager/{id}
  - GET  /routes/getAllByDriver/{id}
  - GET  /routes/get/{id}
  - POST /routes/add
  - DELETE /routes/delete/{id}
  - PUT  /routes/update/{id}

- Checkpoints (`/checkpoints`)
  - GET  /checkpoints/getAll
  - GET  /checkpoints/get/{id}
  - POST /checkpoints/add
  - DELETE /checkpoints/delete/{id}
  - PUT  /checkpoints/update/{id}

- Comments (`/comments`)
  - GET  /comments/getAll
  - GET  /comments/get/{id}
  - POST /comments/add
  - DELETE /comments/delete/{id}
  - PUT  /comments/update/{id}

- Forums (`/forums`)
  - GET  /forums/getAll
  - GET  /forums/get/{id}
  - POST /forums/add
  - DELETE /forums/delete/{id}
  - PUT  /forums/update/{id}

## Example Requests (curl / PowerShell)

Create a cargo (example body):

```json
{
  "name": "Electronics",
  "weight": 1250.5
}
```

curl (Linux/macOS / Git Bash on Windows):

```bash
curl -X POST http://localhost:8080/cargo/add \
  -H "Content-Type: application/json" \
  -d '{"name":"Electronics","weight":1250.5}'
```

PowerShell (Invoke-RestMethod):

```powershell
$body = @{ name = 'Electronics'; weight = 1250.5 } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://localhost:8080/cargo/add -Body $body -ContentType 'application/json'
```

Validate a user (JSON with login and password):

```powershell
$login = @{ login = 'user1'; password = 'pass' } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://localhost:8080/users/validateUser -Body $login -ContentType 'application/json'
```

## Troubleshooting

- If the app fails to start due to DB connectivity, verify `application.properties` and that MySQL is running and accessible.
- If ports are busy, change `server.port` in `application.properties`.

## Notes & Next steps

- Consider adding API documentation (Swagger/OpenAPI).
- Improve validation and error handling on controllers.
- Use DTOs instead of exposing JPA entities directly for public APIs.
