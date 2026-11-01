-- Online Bookstore: full database setup.
-- Idhai eththanai dhadava run pannaalum problem illa (duplicate books varaadhu).
CREATE DATABASE IF NOT EXISTS bookstore_db;
USE bookstore_db;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(60),
    price DECIMAL(8,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample books: books table khaali-aa irundha mattum insert aagum
SET @empty := ((SELECT COUNT(*) FROM books) = 0);

INSERT INTO books (title, author, category, price, stock)
SELECT 'Head First Java', 'Kathy Sierra', 'Programming', 650.00, 20 FROM DUAL WHERE @empty;
INSERT INTO books (title, author, category, price, stock)
SELECT 'Effective Java', 'Joshua Bloch', 'Programming', 720.00, 15 FROM DUAL WHERE @empty;
INSERT INTO books (title, author, category, price, stock)
SELECT 'Wings of Fire', 'A.P.J. Abdul Kalam', 'Biography', 250.00, 30 FROM DUAL WHERE @empty;
INSERT INTO books (title, author, category, price, stock)
SELECT 'Ponniyin Selvan', 'Kalki', 'Fiction', 450.00, 12 FROM DUAL WHERE @empty;
INSERT INTO books (title, author, category, price, stock)
SELECT 'Clean Code', 'Robert C. Martin', 'Programming', 590.00, 10 FROM DUAL WHERE @empty;

-- Register aana piragu, unga account-a ADMIN aakka (email-a maathi, thani-aa run pannunga):
-- UPDATE users SET role = 'ADMIN' WHERE email = 'unga-email@gmail.com';
