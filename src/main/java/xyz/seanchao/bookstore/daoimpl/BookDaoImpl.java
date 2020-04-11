package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.repository.BookRepository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Integer id) {
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
