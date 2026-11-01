
package com.bookstore.bookstore_springboot.repository;

import com.bookstore.bookstore_springboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}