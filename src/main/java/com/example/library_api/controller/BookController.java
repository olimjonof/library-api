package com.example.library_api.controller;

import com.example.library_api.model.Book;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.library_api.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    //Post
    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // GET
    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    // GET
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "ID " + id + " bo'lgan kitob muvaffaqiyatli o'chirildi!";
    }
    //rent
    // 1. Manzilga /{userId} qismini qo'shamiz
    @PutMapping("/{id}/rent/{userId}")
    public Book rent(@PathVariable Long id, @PathVariable Long userId) { // 2. Parametrga Long userId qo'shamiz
        return bookService.rentBook(id, userId); // 3. Servisga ikkala ID-ni uzatamiz
    }

    //return
    @PutMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id) {
        return bookService.returnBook(id);
    }

    // URL: GET http://localhost:8080/api/books/search?author=Abdulla
    @GetMapping("/search")
    public List<Book> searchByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
}
