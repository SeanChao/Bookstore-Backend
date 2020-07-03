package xyz.seanchao.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.seanchao.bookstore.entity.Book;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT book FROM Book book where book.author=?1")
    List<Book> findByAuthor(String author);

    List<Book> findAllByActiveTrue();

    Page<Book> findAllByActiveTrue(Pageable page);

    @Query(value = "CALL bookSalesStat(:start, :end)", nativeQuery = true)
    List<Map<String, Object>> bookSalesStat(@Param("start") Date start,
                                            @Param("end") Date end);
}
