package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;
import xyz.seanchao.bookstore.util.MessageUtil;
import xyz.seanchao.bookstore.util.SessionUtil;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public List<Orders> getAllOrders(@RequestParam(name = "id") Integer userId) {
        return ordersService.findAll(userId);
    }

    @PostMapping("/new")
    public JSONObject addNewOrder(@RequestBody JSONObject itemList) {
        System.out.println("order /new " + itemList);
        if (!SessionUtil.checkAuth()) return MessageUtil.makeMessage(0,
                "permission denied");
        JSONObject user = SessionUtil.getAuth();
        System.out.println("session user in making order: " + user);
        int status = ordersService.addOrder(itemList);
        return MessageUtil.makeMessage(status, "OK");
    }

}
