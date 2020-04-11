package xyz.seanchao.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }
}
