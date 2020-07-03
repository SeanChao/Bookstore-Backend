package xyz.seanchao.bookstore.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Pageable;
import xyz.seanchao.bookstore.entity.Book;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookService {
    Book findBookById(Integer id);

    List<Book> findAll();

    List<Book> findAll(Pageable page);

    List<Book> findAllActive();

    List<Book> findAllActive(Pageable page);

    List<Book> findByAuthor(String author);

    Book updateBook(Integer id, Book book);

    Book addBook(JSONObject book);

    void deleteBook(Integer id);

    List<Map<String, Object>> bookSalesStat(Date start, Date end);
}
