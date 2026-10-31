# Online Bookstore: Spring Boot version

REST API for an online bookstore built with **Spring Boot**, **Spring Data JPA**, **MySQL** and **Spring Security**.

## Features
- Book CRUD REST API (list, get, add, update, delete)
- Validation and JSON error responses
- User registration with BCrypt password hashing
- Login (HTTP Basic) with ADMIN / USER roles
  - Anyone can view books
  - Only ADMIN can add, update or delete books

## Tech stack
Java 17, Spring Boot 4, Spring Data JPA (Hibernate), Spring Security, MySQL 8, Maven

## API endpoints
| Method | URL | Access |
|---|---|---|
| GET | /api/books | Everyone |
| GET | /api/books/{id} | Everyone |
| POST | /api/books | ADMIN |
| PUT | /api/books/{id} | ADMIN |
| DELETE | /api/books/{id} | ADMIN |
| POST | /api/auth/register | Everyone |

## Run locally
1. Create the database in MySQL: `CREATE DATABASE bookstore_spring_db;`
2. Set your MySQL username and password in `src/main/resources/application.properties`
3. Run: `mvn spring-boot:run`
4. Open http://localhost:8081/api/books

Tables (`books`, `users`) are created automatically by Hibernate.

## Make a user ADMIN
Register a user, then run in MySQL:
```sql
UPDATE users SET role = 'ADMIN' WHERE email = 'your-email@example.com';
```

## Project structure
`controller` (REST endpoints), `entity` (Book, User), `repository` (Spring Data JPA), `dto` (request objects), `config` (security and password encoder), `security` (user details service), `exception` (global error handling)

Also built as a Servlet/JSP/JDBC version in the `bookstore-servlet` folder of this repository.
