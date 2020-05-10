package xyz.seanchao.bookstore.service;

import com.alibaba.fastjson.JSONObject;
import xyz.seanchao.bookstore.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll(Integer userId);

    int addOrder(JSONObject order);
}
