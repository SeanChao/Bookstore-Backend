package xyz.seanchao.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/book/{id}")
    public Book findBook(@PathVariable("id") Integer id) {
        System.out.println("Searching book id: " + id);
        Book book = bookService.findBookById(id);
        System.out.println(book.getId() + " " + book.getName());
        return book;
    }

    @GetMapping(value = "/getBookAll")
    public List<Book> getBookAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/author/{name}")
    public List<Book> findByAuthor(@PathVariable("name") String author) {
        System.out.println("find books by author: " + author);
        return bookService.findByAuthor(author);
    }
}
