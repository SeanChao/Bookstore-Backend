package xyz.seanchao.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.service.BookService;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/book")
    public Book findBook(@RequestParam(name = "id") Integer id) {
        System.out.println("Searching book id: " + id);
        Book book = bookService.findBookById(id);
        System.out.println(book.getId() + " " + book.getTitle());
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

    @PutMapping(value = "/updateBook")
    public Book updateBook(@RequestBody Book b) {
        System.out.println(b);
        return bookService.updateBook(b.getId(), b);
    }
}
