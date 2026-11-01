
# Online Bookstore

A bookstore web application built **two ways** to compare a traditional Java EE approach with Spring Boot.

| Version | Folder | Stack |
|---|---|---|
| Servlet / JSP | [`bookstore-servlet`](bookstore-servlet) | Java Servlets, JSP, JSTL, JDBC, MySQL, Maven, Jetty |
| Spring Boot REST API | [`bookstore-springboot`](bookstore-springboot) | Spring Boot, Spring Data JPA, Spring Security, MySQL, Maven |

## Features (both versions)
- Browse and search books
- User registration with securely hashed passwords
- Login with ADMIN / USER roles (only ADMIN can add, update or delete books)

## What I learned from building both
| Topic | Servlet version | Spring Boot version |
|---|---|---|
| Database access | JDBC + DAO with `PreparedStatement` | Spring Data JPA repositories |
| Controllers | `HttpServlet` classes | `@RestController` |
| Password hashing | PBKDF2 (written manually) | BCrypt (Spring Security) |
| Access control | Servlet `Filter` | Spring Security rules |
| Server | Jetty (`mvn jetty:run`) | Embedded Tomcat (`mvn spring-boot:run`) |

Each folder has its own README with setup steps.
