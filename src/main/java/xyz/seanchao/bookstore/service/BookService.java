package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);

    List<Book> findAll();

    List<Book> findByAuthor(String author);
}
