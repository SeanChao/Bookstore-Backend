package xyz.seanchao.bookstore.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import xyz.seanchao.bookstore.entity.Book;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> findAll();

    List<Book> findAll(Pageable page);

    List<Book> findAllActive();

    List<Book> findAllActive(Pageable page);

    List<Book> findByAuthor(String author);

    Book updateBook(Integer id, Book book);

    Book addOne(Book book);

    void deleteOne(Integer id);

    List<Map<String, Object>> bookSalesStat(@Param("start") Date start,
                                            @Param("end") Date end);
}
