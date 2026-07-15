package com.example.library_api.service;

import com.example.library_api.model.Book;
import org.springframework.stereotype.Service;
import com.example.library_api.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Kitob topilmadi !!!"));
    }

    public void deleteBook(Long id){

        getBookById(id);

        bookRepository.deleteById(id);
    }

    // Kitobni ijaraga olish (Rent)
    public Book rentBook(Long id) {
        Book book = getBookById(id);

        if (!book.isAvailable()) {
            throw new RuntimeException("Bu kitob allaqachon ijaraga berilgan!");
        }

        book.setAvailable(false);
        return bookRepository.save(book);
    }

    // Kitobni qaytarish (Return)
    public Book returnBook(Long id) {
        Book book = getBookById(id);

        book.setAvailable(true);
        return bookRepository.save(book);
    }
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

}
