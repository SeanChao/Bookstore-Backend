package xyz.seanchao.bookstore.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.seanchao.bookstore.entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersService {
    List<JSONObject> findAll(Integer userId);

    List<Orders> findUserOrdersByDate(Integer userId, Date from, Date to);


    JSONArray findAllOrdersByDate(Date from, Date to);

    int addOrder(JSONObject order);
}
