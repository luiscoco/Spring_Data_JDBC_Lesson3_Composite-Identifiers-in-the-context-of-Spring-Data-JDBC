# Composite ID Demo

A minimal Spring Boot 3 + Spring Data JDBC sample that demonstrates how to persist aggregates whose primary key is a composite value type. At startup the app writes a sample order into an in-memory H2 database and prints everything it finds through a `CommandLineRunner`.

## Key Features
- Composite primary key backed by the immutable `OrderId` value object.
  
  <img width="893" height="633" alt="image" src="https://github.com/user-attachments/assets/63a4f8f0-7929-4090-8c3a-64fc9b84d9bb" />

  <img width="1017" height="479" alt="image" src="https://github.com/user-attachments/assets/f40a162f-238e-4504-a802-f001a0fa0c13" />

- Schema auto-initialised through `schema.sql`, so the project stays migration-free for classroom use.
- Repository implemented with `NamedParameterJdbcTemplate` so composite identifiers are handled via plain SQL even though Spring Data JDBC does not yet support them as entity IDs.
- H2 in-memory database configured for repeatable runs; no external services required.
- Verbose JDBC logging enabled (`logging.level.org.springframework.data.jdbc=DEBUG`) to visualise the SQL emitted for educational purposes.

## Getting Started
1. **Prerequisites:** JDK 17+ and Maven 3.9+ on your `PATH`.
2. **Install dependencies & run:**  
   ```bash
   mvn spring-boot:run
   ```
3. **Observe the log:** you should see the sample order being inserted and then printed from `orderRepository.findAll()`.

To rerun from a clean state simply stop the app—because the database lives in-memory it gets recreated on every launch.

## Project Layout
- `src/main/java/com/example/compositeid/domain` – aggregate (`Order`) and composite identifier (`OrderId`).
- `src/main/java/com/example/compositeid/repository` – `OrderRepository` contract plus the JDBC-backed implementation.
- `src/main/resources/schema.sql` – creates the `ORDERS` table with a composite primary key (`customer_id`, `order_number`).
- `src/main/resources/application.properties` – H2 datasource config and logging tweaks.

## Extending the Demo
- Replace the `CommandLineRunner` with REST endpoints or Spring Shell commands.
- Add Flyway/Liquibase if you need versioned migrations.
- Introduce integration tests (e.g. using `@DataJdbcTest`) to cover repository behaviour.
