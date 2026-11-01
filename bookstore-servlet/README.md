# Online Bookstore (Servlet + JSP + JDBC + MySQL)

Features: book list, search, add / delete (ADMIN mattum), register, login, logout.

## Run panna steps
1. MySQL Workbench-la `sql/setup.sql` run pannunga (database + tables + sample books). Rendu dhadava run pannaalum problem illa.
2. `src/main/java/com/bookstore/util/DBConnection.java`-la USER / PASSWORD unga MySQL-oda match aanaa maathunga (ippo root / root).
3. Indha folder-la (pom.xml irukkura idam) terminal open panni:  `mvn jetty:run`
4. Browser-la open pannunga:
   - Books:    http://localhost:8080/bookstore/books
   - Register: http://localhost:8080/bookstore/register
   - Login:    http://localhost:8080/bookstore/login

## Admin account
Register pannina piragu Workbench-la (email-a maathunga):

    UPDATE users SET role = 'ADMIN' WHERE email = 'unga-email@gmail.com';

Logout panni thirumba login pannunga. Ippo Add Book / Delete therum.

Requirements: JDK 11+, Maven, MySQL 8.
