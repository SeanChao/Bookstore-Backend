package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;

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
        int status = ordersService.addOrder(itemList);
        JSONObject res = new JSONObject();
        res.put("status", status);
        res.put("msg", "OK");
        return res;
    }

}
