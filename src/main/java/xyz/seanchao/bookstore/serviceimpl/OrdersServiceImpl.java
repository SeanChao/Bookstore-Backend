package xyz.seanchao.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.OrdersDao;
import xyz.seanchao.bookstore.entity.Book;
import xyz.seanchao.bookstore.entity.OrderItem;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.BookService;
import xyz.seanchao.bookstore.service.OrdersService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private BookService bookService;

    @Override
    public List<Orders> findAll(Integer userId) {
        return ordersDao.findAll(userId);
    }

    @Override
    public int addOrder(JSONObject orderInfo) {
        JSONArray orderItems = orderInfo.getJSONArray("items");
        System.out.println(orderItems);
        System.out.println(bookService.findBookById(1));
        Orders order = new Orders((Integer) orderInfo.get("userId"),
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
