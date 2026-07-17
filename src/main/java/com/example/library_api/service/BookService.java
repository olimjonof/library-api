package com.example.library_api.service;

import com.example.library_api.model.Book;
import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.library_api.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
    public Book rentBook(Long id, Long userId) {
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

    public void borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("Foydalanuvchi topilmadi!"));

        Book book = getBookById(bookId);

        // Challenge shu yerda
        if (user.getBorrowedBooks().size() >= 3) {
            throw new RuntimeException("Siz 3 ta kitob olgansiz!");
        }

        if (!book.isAvailable()) {
            throw new RuntimeException("Kitob band!");
        }

        book.setBorrowedBy(user);
        book.setAvailable(false);

        bookRepository.save(book);
    }


}
