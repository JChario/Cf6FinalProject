# CLAUDE.md

## Project Purpose
A Spring Boot "Lending Library" web application for managing a book lending library: users, books, and borrowing records, with authentication and role-based access (ADMIN seeded on startup as `admin` / `admin123`).

## Tech Stack
- Java 17, Spring Boot 3.3.0 (Maven)
- Spring Web (REST controllers), Spring Data JPA, Spring Security, Spring Validation
- Thymeleaf server-side templates (+ thymeleaf-extras-springsecurity6)
- MySQL (mysql-connector-j), Lombok
- springdoc-openapi (Swagger UI) for API docs
- WebJars: Vue 2.6.11, Axios (front-end assets)

## Build / Run / Test
Prerequisite: a running MySQL server with a `book_lending` database, and DB credentials set in `src/main/resources/application.properties` (`spring.datasource.username` / `password`).

- Run: `mvn spring-boot:run` (or `./mvnw spring-boot:run`)
- Build: `mvn clean package`
- Test: `mvn test`
- App URL: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html (API docs at `/v3/api-docs`)

JPA `ddl-auto=update` auto-creates/updates schema; an `admin` user is created on first startup.

## Key Directories
- `src/main/java/com/example/demo/` — application root (`LendingLibraryApplication.java` is the entry point)
  - `config/` — `SecurityConfig`, `OpenAPIConfig`
  - `controller/` — REST + Thymeleaf controllers (Auth, Book, Borrowing, User, Home)
  - `service/` — service interfaces + `*Impl` implementations
  - `repository/` — Spring Data JPA repositories
  - `entity/` — JPA entities (Book, Borrowing, User, Role enum)
  - `dto/`, `mapper/` — DTOs and entity<->DTO mappers
  - `exception/` — `GlobalExceptionHandler`
- `src/main/resources/templates/` — Thymeleaf views (+ `fragments/` for header/footer/navbar)
- `src/main/resources/static/` — static assets (`styles.css`)
- `src/test/java/com/example/demo/` — tests (`DemoApplicationTests`)

## Notable Conventions / Notes
- Base Java package is `com.example.demo` (not aligned with the Maven groupId `com.mixalismavromanolakis`).
- The Maven artifact/name/description in `pom.xml` reference a "car_service_tasks" project; the actual code is the Lending Library app. Treat `pom.xml` metadata as stale.
- Service layer follows interface + `*Impl` pattern.
- Both API (`BookController`) and server-rendered (`BookControllerThymeleaf`) controllers exist.
- `springdoc-openapi-starter-webmvc-ui` is declared twice with different versions (3.0.0 and 2.1.0) in `pom.xml`.
