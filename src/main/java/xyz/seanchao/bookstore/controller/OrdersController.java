package xyz.seanchao.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;
import xyz.seanchao.bookstore.util.MessageUtil;
import xyz.seanchao.bookstore.util.SessionUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public List<JSONObject> getAllOrders(@RequestParam(name = "id") Integer userId) {
        return ordersService.findAll(userId);
    }

    @GetMapping("/user")
    public List<Orders> getUserOrdersByDate(@RequestParam(name = "user_id") Integer uid,
                                            @RequestParam(name = "from") String from,
                                            @RequestParam(name = "to") String to) {
        if (uid == null || from == null || to == null)
            return new ArrayList<>(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFrom = LocalDate.parse(from, formatter);
        LocalDate localDateTo = LocalDate.parse(to, formatter);
        Date dateFrom =
                Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateTo =
                Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("/api/orders/user params: user_id: " + uid + ", " + dateFrom + " -> " + dateTo);
        return ordersService.findUserOrdersByDate(uid, dateFrom, dateTo);
    }

    @GetMapping("/allUser")
    public JSONArray getAllUserOrders() {
        return ordersService.findAllOrdersByDate(new Date(), new Date());
    }

    @PostMapping("/new")
    public JSONObject addNewOrder(@RequestBody JSONObject itemList) {
        System.out.println("order /new " + itemList);
        if (!SessionUtil.checkAuth()) return MessageUtil.makeMessage(0,
                "permission denied");
        JSONObject user = SessionUtil.getAuth();
        System.out.println("session user in making order: " + user);
        int status = ordersService.addOrder(itemList);
        if (status != 0) return MessageUtil.makeMessage(100, "Failed");
        return MessageUtil.makeMessage(status, "OK");
    }

}
