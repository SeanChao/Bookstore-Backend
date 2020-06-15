package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
        return getBooksListImage(bookList);
    }

    @Override
    public List<Book> findAll(Pageable page) {
        List<Book> tmp = bookRepository.findAll(page).getContent();
        ArrayList<Book> bookList = new ArrayList<>(tmp);
        return getBooksListImage(bookList);
    }

    @Override
    public List<Book> findAllActive() {
        return getBooksListImage((ArrayList<Book>) bookRepository.findAllByActiveTrue());
    }

    @Override
    public List<Book> findAllActive(Pageable page) {
        List<Book> tmp = bookRepository.findAllByActiveTrue(page).getContent();
        ArrayList<Book> bookList = new ArrayList<>(tmp);
        return getBooksListImage(bookList);
    }

    private List<Book> getBooksListImage(ArrayList<Book> bookList) {
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
            b.setAuthor(book.getAuthor());
            b.setIsbn(book.getIsbn());
            b.setImage(book.getImage());
            if (book.getActive() != null) b.setActive(book.getActive());
            if (book.getImage() != null && book.getImage().getImageBase64() != null)
                bookImageRepository.findByFid(b.getId()).map((e) -> {
                    e.setImageBase64(book.getImage().getImageBase64());
                    return bookImageRepository.save(e);
                });
            System.out.println("update to " + b);
            return bookRepository.save(b);
        }).orElseGet(() -> bookRepository.save(book));
    }

    @Override
    public Book addOne(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteOne(Integer id) {
        Book b = findOne(id);
        System.out.println("Delete " + b);
        if (b == null) return;
        b.setActive(false);
        updateBook(id, b);
//        bookRepository.delete(findOne(id));
//        Optional<BookImage> bookImageOptional=bookImageRepository.findByFid(id);
//        bookImageOptional.ifPresent(bookImage -> bookImageRepository.delete(bookImage));
    }
}
