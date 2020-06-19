package xyz.seanchao.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.BookImage;
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
    public List<Book> findAll(Pageable page) {
        return bookDao.findAll(page);
    }

    @Override
    public List<Book> findAllActive() {
        return bookDao.findAllActive();
    }

    @Override
    public List<Book> findAllActive(Pageable page) {
        return bookDao.findAllActive(page);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        return bookDao.updateBook(id, book);
    }

    @Override
    public Book addBook(JSONObject data) {
        Book book = new Book();
        System.out.println("service: add book data" + data);
        book.setTitle(data.getString("title"));
        book.setIsbn(data.getString("isbn"));
        book.setCategory(data.getString("category"));
        book.setAuthor(data.getString("author"));
        book.setPrice(data.getBigDecimal("price"));
        book.setDescription(data.getString("description"));
        book.setInventory(data.getIntValue("inventory"));
        book.setActive(true);
        BookImage image = new BookImage();
        image.setImageBase64(data.getString("img"));
        book.setImage(image);
        System.out.println("Service: add book: " + book);
        return bookDao.addOne(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteOne(id);
    }
}
