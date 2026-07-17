package com.example.library_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Kitob nomi bo'sh bo'lishi mumkin emas!")
    @Size(min = 2, message = "Kitob nomi kamida 2 ta harfdan iborat bo'lishi kerak!")
    private String title;

    @NotBlank(message = "Muallif nomi bo'sh bo'lishi mumkin emas!")
    private String author;

    private boolean available = true;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Book(){};

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
