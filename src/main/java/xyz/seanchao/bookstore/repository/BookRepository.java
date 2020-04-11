package xyz.seanchao.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.seanchao.bookstore.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT book FROM Book book where book.author=?1")
    List<Book> findByAuthor(String author);

}
