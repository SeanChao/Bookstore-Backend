package xyz.seanchao.bookstore.service;

import org.springframework.data.domain.Pageable;
import xyz.seanchao.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);

    List<Book> findAll();

    List<Book> findAll(Pageable page);

    List<Book> findByAuthor(String author);

    Book updateBook(Integer id, Book book);

    Book addBook(Book book);
}
