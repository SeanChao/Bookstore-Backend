package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/info")
    public Book findBook(@RequestParam(name = "id") Integer id) {
        System.out.print("/book/info/?id=" + id + " ->");
        Book book = bookService.findBookById(id);
        System.out.println(book);
        return book;
    }

    @GetMapping(value = "/all")
    public List<Book> getBookAll(@RequestParam(name = "limit",
            required = false) Integer limit) {
        System.out.println("/all");
        if (limit != null) {
            System.out.println("limit: " + limit);
            Pageable part = PageRequest.of(0, limit);
            return bookService.findAll(part);
        }
        return bookService.findAll();
    }

    @GetMapping(value = "/author/{name}")
    public List<Book> findByAuthor(@PathVariable("name") String author) {
        System.out.println("find books by author: " + author);
        return bookService.findByAuthor(author);
    }

    @PutMapping(value = "/update")
    public Book updateBook(@RequestBody Book b) {
        System.out.println(b);
        return bookService.updateBook(b.getId(), b);
    }

    @PostMapping(value = "new")
    public Book addBook(@RequestBody JSONObject data) {
        Book book = new Book();
        book.setTitle(data.getString("title"));
        book.setIsbn(data.getString("isbn"));
        book.setCategory(data.getString("category"));
        book.setAuthor(data.getString("author"));
        book.setPrice(data.getBigDecimal("price"));
        book.setDescription(data.getString("description"));
        book.setCover(data.getString("cover"));
        book.setInventory(data.getIntValue("inventory"));
        return bookService.addBook(book);
    }
}
