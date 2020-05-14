package xyz.seanchao.bookstore.dao;

import org.springframework.data.domain.Pageable;
import xyz.seanchao.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> findAll();

    List<Book> findAll(Pageable page);

    List<Book> findByAuthor(String author);

    Book updateBook(Integer id, Book book);

    Book addOne(Book book);
}
