package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.service.CartService;
import xyz.seanchao.bookstore.util.MessageUtil;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/")
    public JSONArray getCartItems() {
        System.out.println(cartService.getCartItems());
        return cartService.getCartItems();
    }

    @PostMapping("/add")
    public JSONObject addCartItems(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("id");
        Integer amount = data.getInteger("amount");
        cartService.addBook(bookId, amount);
        return MessageUtil.makeMessage(0);
    }

    @PostMapping("/update")
    public JSONArray updateCartItems(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("id");
        Integer amount = data.getInteger("amount");
        System.out.println(bookId + " " + amount);
        cartService.updateBook(bookId, amount);
        return cartService.getCartItems();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.checkout();
    }
}
