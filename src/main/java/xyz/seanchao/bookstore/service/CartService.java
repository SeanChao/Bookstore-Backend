package xyz.seanchao.bookstore.service;

import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;

public interface CartService {

    void addBook(Integer bookId, Integer amount);

    void removeBook(Integer product);

    void updateBook(Integer book, Integer amount);

    JSONArray getCartItems();

    void checkout();

    BigDecimal getTotal();

}
