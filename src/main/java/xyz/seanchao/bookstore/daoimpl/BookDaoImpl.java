package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.BookImage;
import xyz.seanchao.bookstore.repository.BookImageRepository;
import xyz.seanchao.bookstore.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImageRepository bookImageRepository;

    @Override
    public Book findOne(Integer id) {
        Book book = bookRepository.getOne(id);
        Optional<BookImage> image = bookImageRepository.findByFid(id);
        if (image.isPresent()) {
            book.setImage(image.get());
        } else {
            book.setImage(null);
            System.err.println("Failed to fetch image for book with id = " + id);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        ArrayList<Book> bookList = (ArrayList<Book>) bookRepository.findAll();
        for (Book book : bookList) {
            Integer id = book.getId();
            Optional<BookImage> image = bookImageRepository.findByFid(id);
            if (image.isPresent()) {
                book.setImage(image.get());
            } else {
                book.setImage(null);
                System.err.println("Failed to fetch image for book with id = " + id);
            }
        }
        return bookList;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        System.out.println("!" + book);
        return bookRepository.findById(id).map((b) -> {
            b.setTitle(book.getTitle());
            b.setInventory(book.getInventory());
            return bookRepository.save(b);
        }).orElseGet(() -> bookRepository.save(book));
    }

    @Override
    public Book addOne(Book book) {
        return bookRepository.save(book);
    }
}
