package xyz.seanchao.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.BookDao;
import xyz.seanchao.bookstore.dao.OrdersDao;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.OrderItem;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<JSONObject> findAll(Integer userId) {
        List<Orders> orders = ordersDao.findAll(userId);
        return orders.parallelStream().map((o) -> {
            List<OrderItem> items = o.getItems();
            JSONArray jItems = new JSONArray();
            for (OrderItem orderItem : items) {
                Book b = bookDao.findOne(orderItem.getBook());
                JSONObject jData = new JSONObject();
                jData.put("book", orderItem.getBook());
                jData.put("amount", orderItem.getAmount());
                jData.put("title", b.getTitle());
                jData.put("author", b.getAuthor());
                jData.put("price", b.getPrice());
                jData.put("img", b.getImage().getImageBase64());
                jItems.add(jData);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", o.getId());
            jsonObject.put("userId", o.getUserId());
            jsonObject.put("time", o.getTime());
            jsonObject.put("items", jItems);
            return jsonObject;
        }).collect(Collectors.toList());
//        return ordersDao.findAll(userId);
    }

    @Override
    public List<Orders> findUserOrdersByDate(Integer userId, Date from, Date to) {
        return ordersDao.findUserOrdersByDate(userId, from, to);
    }

    @Override
    public JSONArray findAllOrdersByDate(Date from, Date to) {
        List<Orders> orders = ordersDao.findAllUserTimeOrders(from, to);
        JSONArray detailedOrders = new JSONArray(orders.size());
        for (Orders oneOrder : orders) {
            System.out.println(oneOrder);
            Integer uid = oneOrder.getUserId();
            User user = userDao.findOne(uid);
            JSONObject userJson = new JSONObject();
            userJson.put("id", uid);
            if (user != null) {
                userJson.put("name", user.getName());
            }
            JSONObject orderJson = new JSONObject();
            orderJson.put("id", oneOrder.getId());
            orderJson.put("time", oneOrder.getTime());
            orderJson.put("user", userJson);
            JSONArray items = new JSONArray();
            for (OrderItem it : oneOrder.getItems()) {
                JSONObject item = new JSONObject();
                item.put("book", it.getBook());
                item.put("amount", it.getAmount());
                items.add(item);
            }
            orderJson.put("items", items);
            detailedOrders.add(orderJson);
        }
        return detailedOrders;
    }

    @Override
    public int addOrder(JSONObject orderInfo) {
        JSONArray orderItems = orderInfo.getJSONArray("items");
        System.out.println(orderItems);
        System.out.println(bookService.findBookById(1));
        Integer userId = (Integer) orderInfo.get("userId");
        if (userId == null) return 2;
        Orders order = new Orders(userId, new Date());
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
                return 1;
            }
            book.setInventory(book.getInventory() - oItem.getAmount());
            bookService.updateBook(book.getId(), book);
        }
        order.setItems(orderItemsList);
        System.out.println(order);
        ordersDao.addOrder(order);
        return 0;
    }
}
