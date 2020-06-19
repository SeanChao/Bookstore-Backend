package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.BookImage;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.util.MessageUtil;

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
            return bookService.findAllActive(part);
        }
        return bookService.findAllActive();
    }

    @GetMapping(value = "/author/{name}")
    public List<Book> findByAuthor(@PathVariable("name") String author) {
        System.out.println("find books by author: " + author);
        return bookService.findByAuthor(author);
    }

    @PutMapping(value = "/update")
    public Book updateBook(@RequestBody JSONObject json) {
        // adapts incoming JSON Object into Book Object
        Book b = new Book();
        b.setId(json.getInteger("id"));
        b.setInventory(json.getInteger("inventory"));
        b.setTitle(json.getString("title"));
        b.setAuthor(json.getString("author"));
        b.setIsbn(json.getString("isbn"));
        BookImage image = new BookImage();
        image.setFid(b.getId());
        image.setImageBase64(json.getString("img"));
        b.setImage(image);
        System.out.println(b);
        return bookService.updateBook(b.getId(), b);
    }

    @PostMapping(value = "/new")
    public Book addBook(@RequestBody JSONObject data) {
        return bookService.addBook(data);
    }

    @PostMapping(value = "/del")
    public JSONObject delBook(@RequestBody JSONObject data) {
        Integer id = data.getInteger("id");
        if (id == null) return MessageUtil.makeMessage(100, "invalid id");
        bookService.deleteBook(id);
        return MessageUtil.makeMessage(0);
    }
}
