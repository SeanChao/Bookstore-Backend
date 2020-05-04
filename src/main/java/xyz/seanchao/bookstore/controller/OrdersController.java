package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.OrderItem;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;

import java.util.ArrayList;
import java.util.Date;
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
        System.out.println("order /all " + itemList);
        JSONArray orderItems = itemList.getJSONArray("items");
        System.out.println(orderItems);
        Orders order = new Orders((Integer) itemList.get("userId"),
                new Date());
        ArrayList<OrderItem> orderItemsList = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            JSONObject item = orderItems.getJSONObject(i);
            OrderItem oItem = new OrderItem((Integer) item.get("id"), (int) item.get("amount"));
            oItem.setOrder(order);
            orderItemsList.add(oItem);
            System.out.println(oItem);
            // Updates corresponding book inventory data
            Book book = bookService.findBookById(oItem.getBook());
            if (book == null) {
                System.err.println("Book not found!");
                JSONObject res = new JSONObject();
                res.put("status", 1);
                res.put("msg", "Invalid Book ID");
                return res;
            }
            book.setInventory(book.getInventory() - oItem.getAmount());
            bookService.updateBook(book.getId(), book);
        }
        order.setItems(orderItemsList);
        System.out.println(order);
        ordersService.addOrder(order);
        JSONObject res = new JSONObject();
        res.put("status", 0);
        res.put("msg", "OK");
        return res;
    }

}
