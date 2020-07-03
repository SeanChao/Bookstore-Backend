package xyz.seanchao.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.service.CartService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private BookDao bookDao;

    private Map<Integer, Integer> booksMap = new HashMap<>();


    /**
     * If book is in the map just increment quantity by 1.
     * If book is not in the map with, add it with quantity 1
     *
     * @param bookId the id of book
     * @param amount amount of the book
     */
    @Override
    public void addBook(Integer bookId, Integer amount) {
        if (booksMap.containsKey(bookId)) {
            booksMap.replace(bookId, booksMap.get(bookId) + amount);
        } else {
            booksMap.put(bookId, amount);
        }
        System.out.println(booksMap);
    }

    @Override
    public void removeBook(Integer book) {
        booksMap.remove(book);
        System.out.println(booksMap);
    }

    @Override
    public void updateBook(Integer book, Integer amount) {
        if (booksMap.containsKey(book))
            if (amount == 0) removeBook(book);
            else
                booksMap.replace(book, amount);
        System.out.println(booksMap);
    }

    /**
     * @return all items in cart
     */
    @Override
    public JSONArray getCartItems() {
        JSONArray bookList = new JSONArray();
        for (Map.Entry<Integer, Integer> entry : booksMap.entrySet()) {
            JSONObject cartItem = new JSONObject();
            Book book = bookDao.findOne(entry.getKey());
            cartItem.put("id", book.getId());
            cartItem.put("title", book.getTitle());
            cartItem.put("price", book.getPrice());
            cartItem.put("author", book.getAuthor());
            cartItem.put("img", book.getImage().getImageBase64());
            cartItem.put("amount", entry.getValue());
            bookList.add(cartItem);
        }
        return bookList;
    }

    /**
     * Checkout will rollback if there is not enough of some book in stock
     */
    @Override
    public void checkout() {
        booksMap.clear();
    }

    @Override
    public BigDecimal getTotal() {
//        return booksMap.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
        return new BigDecimal(1);
    }
}
