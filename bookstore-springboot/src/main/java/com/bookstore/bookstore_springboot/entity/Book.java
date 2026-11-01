
package com.bookstore.bookstore_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title kaali-aa irukka koodaadhu")
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Author kaali-aa irukka koodaadhu")
    @Column(nullable = false, length = 100)
    private String author;

    @Column(length = 60)
    private String category;

    @NotNull(message = "Price kudukkanum")
    @Positive(message = "Price 0-ku mela irukkanum")
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @Min(value = 0, message = "Stock minus-aa irukka koodaadhu")
    @Column(nullable = false)
    private int stock;

    public Book() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}