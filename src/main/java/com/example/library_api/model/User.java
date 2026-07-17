package com.example.library_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ism bo'sh bo'lishi mumkin emas!")
    @Size(min = 2, message = "Ism kamida 2 ta harfdan iborat bo'lishi kerak!")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email bo'sh bo'lishi mumkin emas!")
    @Email(message = "To'g'ri email manzilini kiriting!")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "borrowedBy", cascade = CascadeType.ALL)
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(){}

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
